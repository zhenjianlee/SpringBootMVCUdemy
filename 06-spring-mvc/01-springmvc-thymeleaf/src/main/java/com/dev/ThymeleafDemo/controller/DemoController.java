package com.dev.ThymeleafDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class DemoController {

    @GetMapping("hello")
    public String displayHello(Model theModel){
        theModel.addAttribute("theDate", LocalDateTime.now());
        return "helloworld";
    }

}
