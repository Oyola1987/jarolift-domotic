package com.jarolift.domotic.controller;

import com.jarolift.domotic.service.ActionQueuer;
import com.jarolift.domotic.service.EmailSender;
import com.jarolift.domotic.service.EmailService;
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
    private EmailService emailService;

    @Autowired
    public ESP32Controller(EmailService emailService) {
        this.emailService = emailService;
    }


//    @GetMapping(value = "/api/esp32/update", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity pushButton(@RequestHeader(value = "User-Agent") String userAgent) {
//        return response(new RequestModel(button, channel, userAgent));
//    }

    @GetMapping(value = "/api/esp32/low-battery", produces = MediaType.APPLICATION_JSON_VALUE)
    public void downMiddleButton(@RequestHeader(value = "User-Agent") String userAgent) {
        emailService.sendLowBatteryEmail(userAgent);
    }
}