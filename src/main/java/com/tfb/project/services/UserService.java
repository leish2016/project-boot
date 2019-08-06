package com.tfb.project.services;


import com.tfb.project.common.Result;
import com.tfb.project.domain.dto.UserReq;
import com.tfb.project.domain.entity.Userinfo;
import com.tfb.project.mapper.UserinfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@Slf4j
public class UserService {

    @Autowired
    UserinfoMapper userInfoMapper;

    public Result addUser(UserReq userReq) {
        Userinfo u = new Userinfo();
        BeanUtils.copyProperties(userReq, u);
        u.setCreateAt(new Date());
        userInfoMapper.insert(u);
        return Result.genSuccessResult(u);
    }

    public Result delUser(Long id){
        userInfoMapper.deleteByPrimaryKey(id);
        return Result.genSuccessResult();
    }

    public Result updateUser(Userinfo u){
        userInfoMapper.updateByPrimaryKeySelective(u);
        return Result.genSuccessResult(u);
    }

    public Result getAll(){
        return Result.genSuccessResult(userInfoMapper.selectAll());
    }

    

}
