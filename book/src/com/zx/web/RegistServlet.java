package com.zx.web;

import com.sun.net.httpserver.HttpServer;
import com.zx.pojo.User;
import com.zx.service.UserService;
import com.zx.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/***************
 * ·                    已经把LoginServlet和RegistServlet合并到UserServlet
 * **************************/

public class RegistServlet extends HttpServlet {

    //web层智能调用service层，不能直接调用dao层，需要实例一个service对象
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
//        销毁
        req.getSession().invalidate();
//        1.获取请求的参数

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

//        2.检查验证码是否正确

//        先写成死的。假定abcd

//        正确
        if (token != null && token.equalsIgnoreCase(code)){

//            3. 检查用户名和密码是否可以
            if (userService.existsUsername(username)){
                //true标识存在
//          不可用
//              跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.html").forward(req,resp);
                System.out.println("用户名已经存在");
            }
            else {
                //false标识username不存在
//                可用
                userService.registerUser(new User(null,username,password,email));
//         4 调用service保存到数据库
//              跳转注册成功页面regist.success。html
                req.getRequestDispatcher("/pages/user/regist_success.html").forward(req,resp);
            }

//

        }
        else {
            //****************记住啊，别忘了**************//
            System.out.println(code+"验证码错误");
            req.getRequestDispatcher("/pages/user/regist.html").forward(req,resp);
            // 验证码不正确
            // 跳回注册页面
        }
//

    }
}
