package com.zx.web;

import com.zx.pojo.User;
import com.zx.service.UserService;
import com.zx.service.impl.UserServiceImpl;
import com.zx.utils.JdbcUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/***************
 *                  已经把LoginServlet和RegistServlet合并到UserServlet
 *                  **************************/
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
//        2.判断用户密码是否正确
        if (userService.login(new User(username,password))==null){
            //密码不对
            req.getRequestDispatcher("/pages/user/login.html").forward(req,resp);
        }
        else {
            //密码对
            req.getRequestDispatcher("/pages/user/login_success.html").forward(req,resp);
        }


    }
}
