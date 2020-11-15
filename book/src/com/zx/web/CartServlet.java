package com.zx.web;

import com.zx.pojo.Book;
import com.zx.pojo.Cart;
import com.zx.pojo.CartItem;
import com.zx.service.BookService;
import com.zx.service.impl.BookServiceImpl;
import com.zx.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;


public class CartServlet extends BaseServlet{

    BookService bookService = new BookServiceImpl();
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        //通过bookService.queryBookForSimple(id)来获取图书
        Book book = bookService.queryBookForSimple(id);
        //把图书信息封装到CartItem中
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //判断是否是第一件商品，用来决定是否new一个购物车，如不进行判断全是new，则只可以拿到一个商品，也就是一个购物车放一个，前边的购物车全扔了
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

        //在首页数据显示添加的内容
        req.getSession().setAttribute("lastName",cartItem.getName());

        //添加完后重定向， 这个可以在请求头里找到referer的信息传到resp.sendRedirect
        resp.sendRedirect(req.getHeader("Referer"));
    }
    /**
    * @Description：[用一句话描述方法] 在购物车修改商品数量
    * @Param
    * @return
    */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取要修改书籍的id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"),1);

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null){
            cart.updateCount(id,count);
            System.out.println(cart.getTotalCount());
        }

        resp.sendRedirect(req.getHeader("referer"));

    }/**
    * @Description：[用一句话描述方法] 删除商品项
    * @Param
    * @return
    */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取要修改书籍的id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null){
            cart.deleteItem(id);
        }

        resp.sendRedirect(req.getHeader("referer"));
    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Cart cart = (Cart) req.getSession().getAttribute("cart");
    cart.clear();
    resp.sendRedirect(req.getHeader("referer"));

    }
    }
