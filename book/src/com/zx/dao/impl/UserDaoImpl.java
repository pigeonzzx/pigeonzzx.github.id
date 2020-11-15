package com.zx.dao.impl;

import com.zx.dao.UserDao;
import com.zx.pojo.User;

public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public User queryByUsername(String username) {
        String sql = "select username,password,email from t_user where username=?";
        return queryForSimple(User.class,sql,username);


    }

    @Override
    public User queryByUsernameAndPaaword(String username, String password) {
        String sql = "select id,username,password,email from t_user where username=? and password=?";
        return queryForSimple(User.class,sql,username,password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(username,password,email) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}

