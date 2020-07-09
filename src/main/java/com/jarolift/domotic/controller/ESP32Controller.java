package com.jarolift.domotic.controller;

import com.jarolift.domotic.service.ESP32Versions;
import com.jarolift.domotic.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ESP32Controller {
    private EmailService emailService;
    private ESP32Versions esp32Versions;

    @Autowired
    public ESP32Controller(EmailService emailService, ESP32Versions esp32Versions) {
        this.emailService = emailService;
        this.esp32Versions = esp32Versions;
    }

    @GetMapping(value = "/api/esp32/version", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getLastVersion(@RequestHeader(value = "User-Agent") String userAgent) {
        return esp32Versions.getLastVersion(userAgent);
    }

    @GetMapping(value = "/api/esp32/update", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Resource> updateOTA(@RequestHeader(value = "User-Agent") String userAgent) {
        ByteArrayResource resource = esp32Versions.getLastFile(userAgent);

        return resource == null ? null : ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping(value = "/api/esp32/low-battery", produces = MediaType.TEXT_PLAIN_VALUE)
    public void sendEmaiLowBattery(@RequestHeader(value = "User-Agent") String userAgent) {
        emailService.sendLowBatteryEmail(userAgent);
    }
}