package com.dev.CorePractise.Controller;


import com.dev.CorePractise.Service.OrderService;

import com.dev.CorePractise.Entity.Order;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    private OrderService orderService;
    private OrderService anotherOrderService;

    //Note A :- Use @Qualifier("orderServiceImplMobile") to choose one implementation of the Service Class [Use Qualifier, more specific and high priority)
    //Note B :- OR use @Primary at the dependency! Don't mix @Qualifier or Primary!
    public Controller(@Qualifier("Software") OrderService orderService,
                      @Qualifier("orderServiceImplMobile") OrderService anotherorderService){
        this.orderService=orderService;
        this.anotherOrderService=anotherorderService;
    }


    @GetMapping("/order")
    public Order getAnOrder(){
        return orderService.getOneOrder();
    }

    @GetMapping("/orders")
    public List<Order> getManyOrders(@PathVariable int num){
        return orderService.generateTheOrders(num);
    }

    @GetMapping("/check")
    public String check(){
        return "Comparing beans : orderService == anotherorderService is:"
                + (orderService == anotherOrderService);
    }

}
