package com.jarolift.domotic.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class EmailService {
    private final static String SUBJECT = "ESP32 Low battery";
    private static Logger logger = LogManager.getLogger(EmailService.class);
    private Map<String, Date> sentEmails = new HashMap<>();
    private EmailSender emailSender;

    @Autowired
    public EmailService(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendLowBatteryEmail(final String userAgent) {
        Date date = sentEmails.get(userAgent);
        Date now = new Date();

        if (date == null || getDaysDiff(date, now) > 7) {
            try {
                emailSender.sendEmail(SUBJECT, userAgent);
                sentEmails.put(userAgent, new Date());
                logger.info("[USER AGENT]: " + userAgent);
                logger.info("[EMAIL] Low battery sent");
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error user agent: " + userAgent);
                logger.error("Error message: " + e.getMessage());
            }
        } else {
            logger.info("[USER AGENT]: " + userAgent);
            logger.info("[EMAIL] Not enough days have passed since the last email: " + getDaysDiff(date, now));
        }
    }

    private long getDaysDiff(Date pastDate, Date date) {
        long diffInMillies = date.getTime() - pastDate.getTime();
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
}
