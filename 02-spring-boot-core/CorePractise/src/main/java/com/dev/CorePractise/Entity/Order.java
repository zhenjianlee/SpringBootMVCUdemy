package com.dev.CorePractise.Entity;

import com.dev.CorePractise.Service.OrderService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
//@Lazy
public class Order {

    public static long prevId=0;
    private long id;
    private LocalDate orderDate;
    private String remarks;
    private double total;

    public Order(){
        ++prevId;
        System.out.println("In constructor: "+getClass().getSimpleName());
    }
    public Order(LocalDate orderDate, String remarks, double total) {
        this();
        this.id=prevId;
        this.orderDate = orderDate;
        this.remarks = remarks;
        this.total = total;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public static List<Order> generateRandomOrders(int num){
        List<Order> randomOrders = new ArrayList<>();
        Random random = new Random();
        for (int i=0 ; i<num;i++){
            //System.out.println("LocalDate "+ randoDate.toString());
            int randoYear= random.nextInt(1987,2024);
            int randoMonth = random.nextInt(1,12);
            int randoDay = randoMonth ==2? random.nextInt(1,28): random.nextInt(1,30);
            LocalDate randoDate = LocalDate.of(randoYear,randoMonth,randoDay);
            int randoStrLen = random.nextInt(0,25);
            String randoStr = "";
            for(int j=0; j< randoStrLen; j++){
                randoStr+= (char)(random.nextInt(48,90));
            }
            double randoTotal = random.nextDouble(1.00,10000);
            randomOrders.add(new Order(randoDate,randoStr,randoTotal));
        }
        return randomOrders;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", remarks='" + remarks + '\'' +
                ", total=" + total +
                '}';
    }
}
