package com.udemy.jian.demo.practise.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicRestController {

    @Value("${developer.name}")
    private String dev;

    @Value("${developer.num}")
    private int num;

    @GetMapping("/")
    public String sayHello(){
        return "Hello World!" + dev +" "+ num;
    }
    @GetMapping("/study")
    public String study(){
        return dev+ " studying hard";
    }

    @GetMapping("/leetcode")
    public String leetcode(){
        return dev+ " leetcoding";
    }
}
