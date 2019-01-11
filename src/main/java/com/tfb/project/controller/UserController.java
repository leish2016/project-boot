package com.tfb.project.controller;


import com.tfb.project.domain.dto.UserReq;
import com.tfb.project.domain.entity.Userinfo;
import com.tfb.project.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(description = "用户操作服务")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    @ApiOperation("添加用户")
    public Userinfo addUserInfo(@RequestBody @Valid UserReq userReq){
        return userService.addUser(userReq);
    }


    @GetMapping("/users")
    @ApiOperation("查询所有用户")
    public List<Userinfo> findAllUserInfo() {
        return userService.getAll();
    }
}
