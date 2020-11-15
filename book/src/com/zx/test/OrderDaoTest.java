package com.zx.test;

import com.zx.dao.OrderDao;
import com.zx.dao.impl.OrderDaoImpl;
import com.zx.pojo.Order;
import com.zx.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderDaoTest {

    @Test
    public void saveOrder() {
        OrderDao order = new OrderDaoImpl();
        order.saveOrder(new Order("1111111", new Date(),new BigDecimal(100),0,1));
    }
}