package com.zx.service.impl;

import com.zx.dao.UserDao;
import com.zx.dao.impl.UserDaoImpl;
import com.zx.pojo.User;
import com.zx.service.UserService;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();
    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);

    }

    @Override
    public User login(User user) {
        return userDao.queryByUsernameAndPaaword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if (userDao.queryByUsername(username) == null){
            return false;
        }
        return true;
    }
}
