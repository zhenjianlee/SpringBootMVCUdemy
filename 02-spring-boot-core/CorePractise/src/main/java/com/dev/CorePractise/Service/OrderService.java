package com.dev.CorePractise.Service;

import com.dev.CorePractise.Entity.Order;

import java.util.List;

public interface OrderService {



    public List<Order> generateTheOrders(int num);

    public Order getOneOrder();

}
