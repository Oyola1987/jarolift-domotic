package com.jarolift.domotic.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @RequestMapping("/helloworld")
    public String index() {
        System.out.println("Called");
        return "Greetings from Spring Boot!";
    }
}
