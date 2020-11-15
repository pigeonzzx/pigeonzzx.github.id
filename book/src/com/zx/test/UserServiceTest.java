package com.zx.test;

import com.zx.pojo.User;
import com.zx.service.UserService;
import com.zx.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserService userService = new UserServiceImpl();
    @Test
    public void registerUser() {
        userService.registerUser(new User(null,"apig","zhuzhu","zhuzhu.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User("caixukun","rap")));
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("caixukun")){
            System.out.println("用户名已经存在了");

        }
        else {
            System.out.println("用户名可用");
        }
    }
}