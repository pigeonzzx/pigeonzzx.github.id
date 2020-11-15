package com.zx.service;

import com.zx.pojo.Cart;

public interface OrderService {
    public String createOrder(Cart cart,int userId);
}
