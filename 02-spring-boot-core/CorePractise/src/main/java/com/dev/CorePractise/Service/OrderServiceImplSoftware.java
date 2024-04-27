package com.dev.CorePractise.Service;

import com.dev.CorePractise.Entity.Order;

import java.util.List;

//Not using @Component on purpose! Demo use case for @Configuration , refer OrderConfiguration!
public class OrderServiceImplSoftware implements OrderService {

    @Override
    public List<Order> generateTheOrders(int num) {
        return Order.generateRandomOrders(num);
    }

    @Override
    public Order getOneOrder() {
        return new Order();
    }
}
