package com.zx.test;

import com.zx.dao.UserDao;
import com.zx.dao.impl.UserDaoImpl;
import com.zx.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {
    UserDao userDao = new UserDaoImpl();
    @Test
    public void queryByUsername() {

//        System.out.println(userDao.queryByUsername("aa"));
        if (userDao.queryByUsername("admin")== null){
            System.out.println("用户名可以用");

        }else {
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void queryByUsernameAndPaaword() {

//        System.out.println(userDao.queryByUsername("aa"));
        if (userDao.queryByUsernameAndPaaword("admin","admin")== null){
            System.out.println("密码或用户名错误，登录失败");

        }else {
            System.out.println("登录成功");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null,"aa","123","123.email")));
    }
}