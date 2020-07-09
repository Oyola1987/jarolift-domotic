package com.jarolift.domotic.controller;

import com.jarolift.domotic.service.ActionQueuer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ESP32Controller {
    private static Logger logger = LogManager.getLogger(ESP32Controller.class);

//    @GetMapping(value = "/api/esp32/update", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity pushButton(@RequestHeader(value = "User-Agent") String userAgent) {
//        return response(new RequestModel(button, channel, userAgent));
//    }

    @GetMapping(value = "/api/esp32/low-battery", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> downMiddleButton(@RequestHeader(value = "User-Agent") String userAgent) {
        System.out.println("Read Specific Enviornment Variable");
        System.out.println("JAVA_HOME Value:- " + System.getenv("JAVA_HOME"));

        System.out.println("\nRead All Variables:-\n");

        Map<String, String> map = System.getenv();
//        for (Map.Entry <String, String> entry: map.entrySet()) {
//            System.out.println("Variable Name:- " + entry.getKey() + " Value:- " + entry.getValue());
//        }
        return map;
    }
}