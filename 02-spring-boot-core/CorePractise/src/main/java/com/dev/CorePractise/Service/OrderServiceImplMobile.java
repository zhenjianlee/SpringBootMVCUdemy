package com.dev.CorePractise.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.dev.CorePractise.Entity.Order;

import java.time.LocalDate;
import java.util.List;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//@Primary // using Qualifier
public class OrderServiceImplMobile implements OrderService{

    private List<Order> list;

    @Override
    public List<Order> generateTheOrders(int num) {
        return Order.generateRandomOrders(num);
    }

    @Override
    public Order getOneOrder() {
        return new Order(LocalDate.now(),"Ordered Mobile Phone!",3000.25);
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
