package com.tfb.project.controller;


import com.tfb.project.common.Result;
import com.tfb.project.domain.dto.UserReq;
import com.tfb.project.domain.entity.Userinfo;
import com.tfb.project.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author leish
 */
@RestController
@RequestMapping("/api/user")
@Api(description = "用户操作服务")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    @ApiOperation("添加用户")
    public Result addUserInfo(@RequestBody @Valid UserReq userReq){
        return userService.addUser(userReq);
    }


    @DeleteMapping("/{id}")
    @ApiOperation("删除用户")
    public Result delUserInfo(@PathVariable Long id){
        return userService.delUser(id);
    }

    @PutMapping
    @ApiOperation("修改用户")
    public Result updateUser(Userinfo u){
        return userService.updateUser(u);
    }


    @GetMapping
    @ApiOperation("查询所有用户")
    public Result findAllUserInfo() {
        return userService.getAll();
    }
}
