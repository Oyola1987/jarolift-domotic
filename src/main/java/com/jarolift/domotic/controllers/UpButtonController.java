package com.jarolift.domotic.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpButtonController {
    @RequestMapping("/api/")
    public String index() {
        return "up button clicked";
    }
}
