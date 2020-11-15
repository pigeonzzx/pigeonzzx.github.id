package com.zx.web;

import com.zx.pojo.User;
import com.zx.service.UserService;
import com.zx.service.impl.UserServiceImpl;
import com.zx.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();
    /**
    * @Description：[用一句话描述方法]注销用户 ，通过消除session搞定
    * @Param
    * @return
    */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        //重定向到工程路径
        resp.sendRedirect(req.getContextPath());

    }


/**
* @Description：[用一句话描述方法]   登录
* @Param
* @return
*/
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //调用userService.login登录处理业务
        User loginUser = userService.login(new User(username,password));
//        2.判断用户密码是否正确
        if (loginUser == null){
            //密码不对
            req.setAttribute("msg", "用户或密码错误！");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }
        else {
            //密码对
            //保存用户登录之后的信息。全局都可以使用
            req.getSession().setAttribute("username",username);
            req.getSession().setAttribute("user",loginUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }
/**
* @Description：[用一句话描述方法]   注册username和password
* @Param
* @return
*/
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        //        1.获取请求的参数
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //        销毁
        req.getSession().invalidate();

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

//        2.检查验证码是否正确   先写成死的。假定abcd
//        正确
        if (token!= null && token.equalsIgnoreCase(code)){

//            3. 检查用户名和密码是否可以
            if (userService.existsUsername(username)){
                //true标识存在
//          不可用
//              跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
                System.out.println("用户名已经存在");
            }
            else {
                //false标识username不存在
//                可用
                //此处可使用BeanUtils类，可省略  1.  getparameter（），也不许往userService传数据
//                userService.registerUser(new User(null,username,password,email));
                //下边注释代码封装进了一个WebUtils工具类。。。进入演示
                /*try {
                    BeanUtils.populate(user,req.getParameterMap());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }*/
                User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
                userService.registerUser(user);
//         4 调用service保存到数据库
                //将username显示到页面上
                req.getSession().setAttribute("username",username);
//                req.getSession().setAttribute("user",user);

//              跳转注册成功页面regist.success。html
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
//                req.getRequestDispatcher("/pages/user/regist_success.html").forward(req,resp);
            }

        }
        else {
            //****************记住啊，别忘了**************//
            System.out.println(code+"验证码错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            // 验证码不正确
            // 跳回注册页面
        }
    }



}
