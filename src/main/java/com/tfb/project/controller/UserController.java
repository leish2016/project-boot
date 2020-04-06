package com.tfb.project.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.tfb.project.common.Result;
import com.tfb.project.domain.dto.UserReq;
import com.tfb.project.domain.entity.Userinfo;
import com.tfb.project.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

/**
 * @author leish
 */
@RestController
@RequestMapping("/api/user")
@Api(tags="用户操作服务")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    @ApiOperation("添加用户")
    public Result addUserInfo(@RequestBody @Valid UserReq userReq) throws JsonProcessingException {
        return userService.addUser(userReq);
    }


    @DeleteMapping("/{id}")
    @ApiOperation("删除用户")
    public Result delUserInfo(@PathVariable Long id){
        return userService.delUser(id);
    }

    @PutMapping
    @ApiOperation("修改用户")
    public Result updateUser(Userinfo u) throws JsonProcessingException {
        return userService.updateUser(u);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据ID查询用户信息")
    public Result findUserInfo(@PathVariable Long id) throws IOException {
        return userService.getUser(id);
    }

    @GetMapping
    @ApiOperation("查询所有用户")
    public Result findAllUserInfo() {
        return userService.getAll();
    }
}
