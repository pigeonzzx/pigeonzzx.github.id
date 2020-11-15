package com.zx.test;

import com.zx.dao.OrderDao;
import com.zx.dao.OrderItemDao;
import com.zx.dao.impl.OrderDaoImpl;
import com.zx.dao.impl.OrderItemDaoImpl;
import com.zx.pojo.Cart;
import com.zx.pojo.CartItem;
import com.zx.pojo.OrderItem;
import com.zx.service.OrderService;
import com.zx.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceTest {

    @Test
    public void createOrder() {

        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"花园宝宝",1,new BigDecimal(200),new BigDecimal(200)));
        cart.addItem(new CartItem(2,"此生无悔二次元",1,new BigDecimal(200),new BigDecimal(200)));
        cart.addItem(new CartItem(3,"那就这样吧",1,new BigDecimal(200),new BigDecimal(200)));

        OrderService orderService = new OrderServiceImpl();
        orderService.createOrder(cart,1);
    }
}