package com.zx.service;

import com.zx.pojo.User;

public interface UserService {

    /**
    * @Description：[注册用户]
    * @Param user
    * @return 1表示成功 -1 失败
    */
    public void registerUser(User user);

    /**
    * @Description：[登录]
    * @Param user
    * @return
    */
    public User login(User user);



    /**
    * @Description：[判断用户名是否存在]
    * @Param
    * @return true表示用户名已存在，false表示不存在。。
    */
    public boolean existsUsername(String username);


}
