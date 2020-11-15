package com.zx.service.impl;

import com.zx.dao.BookDao;
import com.zx.dao.OrderDao;
import com.zx.dao.OrderItemDao;
import com.zx.dao.impl.BookDaoimpl;
import com.zx.dao.impl.OrderDaoImpl;
import com.zx.dao.impl.OrderItemDaoImpl;
import com.zx.pojo.*;
import com.zx.service.OrderService;

import java.util.Date;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoimpl();
    @Override
    public String createOrder(Cart cart, int userId) {

        //设置订单的唯一，使用时间戳+userId
        String orderId = System.currentTimeMillis() + userId + "";
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        orderDao.saveOrder(order);
        //使用foreach循环拿到每一个商品信息
//        int i = 1/0;
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()
                ) {
            CartItem cartItem = entry.getValue();
            //保存订单中的每个商品到t_orderitem中
            orderItemDao.saveOrderItem(new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId));
            //获取每个book,修改销售量和库存
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDao.updateBook(book);
        }
        cart.clear();
        return orderId;
    }
}
