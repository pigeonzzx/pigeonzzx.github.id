package com.zx.web;

import com.zx.dao.impl.BaseDao;
import com.zx.pojo.Cart;
import com.zx.pojo.User;
import com.zx.service.OrderService;
import com.zx.service.impl.OrderServiceImpl;
import com.zx.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从session中获取cart对象和用户id
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User loginUser = (User) req.getSession().getAttribute("user");
        //没有登录结账会造成空指针异常
        if (loginUser == null){
            req.getRequestDispatcher("pages/user/login.jsp").forward(req,resp);
            return;
        }
        //生成订单
        Integer userId = loginUser.getId();
        String orderId = orderService.createOrder(cart, userId);
        req.getSession().setAttribute("orderId",orderId);
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }
}
