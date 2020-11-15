package com.zx.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
//        System.out.println(action);
        //下面方法如果加入其他多种按钮，需要多个if，。。。才用反射机制方便超级多
        /*if ("login".equals(action)){
            login(req,resp);
        }else if ("resist".equals(action)){
            regist(req,resp);
        }*/

        try {

            //this是精髓所在。。。。。。。。。瞬间灵活起来(好像不是这个样子。再想想)
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            //调用目标业务
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            //此处不抛出异常，filter那就接收不到异常就起不到事务管理的效果
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


}
