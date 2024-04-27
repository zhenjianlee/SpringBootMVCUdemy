package com.dev.CorePractise.Config;

import com.dev.CorePractise.Entity.Order;
import com.dev.CorePractise.Service.OrderService;
import com.dev.CorePractise.Service.OrderServiceImplSoftware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfiguration {

    // We use this as an alternative to @Component in the Service class
    @Bean("Software") //bean ID
    public OrderService orderServiceImplSoftware(){
        return new OrderServiceImplSoftware(); // bean ID defaults to method name
    }

}
