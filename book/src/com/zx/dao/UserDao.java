package com.zx.dao;

import com.zx.pojo.User;

public interface UserDao {

    /**
    * @Description：[通过用户名判断是否重复]
    * @Param
    * @return
    */
    public User queryByUsername(String username);
/**
* @Description：[判断用户密码是否正确]
* @Param
* @return
*/
    public User queryByUsernameAndPaaword(String username,String password);
    /**
    * @Description：[保存用户名和密码]
    * @Param
    * @return
    */
    public int saveUser(User user);
}
