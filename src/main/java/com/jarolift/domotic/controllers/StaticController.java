package com.jarolift.domotic.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
public class StaticController {
    @RequestMapping("/")
    public String index() {
        return "index.html";
    }
}