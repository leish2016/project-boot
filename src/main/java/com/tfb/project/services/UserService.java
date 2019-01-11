package com.tfb.project.services;


import com.tfb.project.domain.dto.UserReq;
import com.tfb.project.domain.entity.Userinfo;
import com.tfb.project.mapper.UserinfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class UserService {

    @Autowired
    UserinfoMapper userInfoMapper;

    public Userinfo addUser(UserReq userReq) {
        Userinfo u = new Userinfo();
        BeanUtils.copyProperties(userReq, u);
        u.setCreateAt(new Date());
        userInfoMapper.insert(u);
        return userInfoMapper.selectOne(u);
    }

    public List<Userinfo> getAll(){
        return userInfoMapper.selectAll();
    }

    

}
