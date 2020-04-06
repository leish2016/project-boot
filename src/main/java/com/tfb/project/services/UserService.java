package com.tfb.project.services;


import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.LFUCache;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tfb.project.common.Result;
import com.tfb.project.domain.dto.UserReq;
import com.tfb.project.domain.entity.Userinfo;
import com.tfb.project.mapper.UserinfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;


/**
 * 处理用户信息并缓存
 * @author LSH
 */
@Service
@Slf4j
public class UserService {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    UserinfoMapper userInfoMapper;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    LFUCache<Long,Userinfo> userCache = CacheUtil.newLFUCache(2000);

    public Result addUser(UserReq userReq) throws JsonProcessingException {
        Userinfo u = new Userinfo();
        BeanUtils.copyProperties(userReq, u);
        u.setCreateTime(new Date());
        userInfoMapper.insert(u);
        userCache.put(u.getId(),u);
        stringRedisTemplate.opsForValue().set(u.getId().toString(),mapper.writeValueAsString(u));
        return Result.genSuccessResult(u);
    }

    public Result delUser(Long id){
        userInfoMapper.deleteByPrimaryKey(id);
        userCache.remove(id);
        stringRedisTemplate.delete(id.toString());
        return Result.genSuccessResult();
    }

    public Result updateUser(Userinfo u) throws JsonProcessingException {
        u.setUpdateTime(new Date());
        userInfoMapper.updateByPrimaryKeySelective(u);
        userCache.put(u.getId(),u);
        stringRedisTemplate.opsForValue().set(u.getId().toString(),mapper.writeValueAsString(u));
        return Result.genSuccessResult(u);
    }

    public Result getUser(Long id) throws IOException {
        if(userCache.containsKey(id)){
            Userinfo cacheUser = userCache.get(id);
            log.info("userCache getUser:{}",cacheUser);
            return Result.genSuccessResult(cacheUser);
        }
        if(stringRedisTemplate.hasKey(id.toString())){
            Userinfo redisUser = mapper.readValue(stringRedisTemplate.opsForValue().get(id.toString()),Userinfo.class);
            log.info("redisCache getUser:{}",redisUser);
            return Result.genSuccessResult(redisUser);
        }
        Userinfo paramUser = new Userinfo();
        paramUser.setId(id);
        Userinfo userinfo = userInfoMapper.selectOne(paramUser);
        userCache.put(id,userinfo);
        return Result.genSuccessResult(userinfo);
    }

    public Result getAll(){
        return Result.genSuccessResult(userInfoMapper.selectAll());
    }

    

}
