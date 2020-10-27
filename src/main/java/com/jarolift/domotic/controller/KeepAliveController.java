package com.jarolift.domotic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KeepAliveController {
    @GetMapping(value = "/api/keepalive")
    public ResponseEntity pushButton() {
        return ResponseEntity.ok().body("KEEP ALIVE OK");
    }
}
