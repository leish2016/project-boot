package com.tfb.project.services;

import com.tfb.project.StartAppTests;
import com.tfb.project.common.Result;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest extends StartAppTests {

    @Autowired
    UserService userService;

    @Test
    public void addUser() {
    }

    @Test
    public void delUser() {
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void getAll() {
        Assert.assertEquals(Result.SUCCESS,userService.getAll().getRespCode());
    }
}
