package com.jarolift.domotic.controller;

import com.jarolift.domotic.service.ESP32Versions;
import com.jarolift.domotic.service.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ESP32Controller {
    private static final String NO_FILES = "No 'bin' files";
    private static Logger logger = LogManager.getLogger(ESP32Controller.class);
    private EmailService emailService;
    private ESP32Versions esp32Versions;

    @Autowired
    public ESP32Controller(EmailService emailService, ESP32Versions esp32Versions) {
        this.emailService = emailService;
        this.esp32Versions = esp32Versions;
    }

    @GetMapping(value = "/api/esp32/version/lastest", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getLastVersion(@RequestHeader(value = "User-Agent") String userAgent) {
        File file = esp32Versions.getLastestVersion();
        String version = file != null ? file.getName() : NO_FILES;
        logger.info("[USER AGENT]: " + userAgent);
        logger.info("[LAST VERSION]: " + version);
        return file == null ? null : version;
    }

    @GetMapping(value = "/api/esp32/update/lastest", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Resource> updateOTA(@RequestHeader(value = "User-Agent") String userAgent) throws IOException {
        File file = esp32Versions.getLastestVersion();
        String version = file != null ? file.getName() : NO_FILES;
        logger.info("[USER AGENT]: " + userAgent);
        logger.info("[LAST VERSION]: " + version);
        if(file == null)
            return null;

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping(value = "/api/esp32/low-battery", produces = MediaType.TEXT_PLAIN_VALUE)
    public void sendEmaiLowBattery(@RequestHeader(value = "User-Agent") String userAgent) {
        emailService.sendLowBatteryEmail(userAgent);
    }
}