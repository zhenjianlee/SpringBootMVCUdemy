package com.dev.CorePractise.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dev.CorePractise.Entity.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class OrderServiceImplElectronics implements OrderService{

    private List<Order> list;


    @Override
    public List<Order> generateTheOrders(int num) {
        return Order.generateRandomOrders(num);
    }

    @Override
    public Order getOneOrder() {
        return new Order(LocalDate.now(),"Ordered Electronics!",new Random().nextDouble(0,10000.00));
    }

    @PostConstruct // this runs after bean is created
    public void generateOrders(){
        list = Order.generateRandomOrders(20);
        list.forEach(System.out::println);
    }

    @PreDestroy // this runs before shutdown. this wont run on protytpe scope beans!
    public void cleanUpList(){
        list.clear();
        System.out.println("List has been cleared!");
    }
}
