package com.zx.test;

import com.zx.dao.OrderItemDao;
import com.zx.dao.impl.OrderItemDaoImpl;
import com.zx.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(null,"从入门到入土",1,new BigDecimal(100),new BigDecimal(100),"1111111"));
    }
}