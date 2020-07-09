package com.jarolift.domotic.service;

import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Component
public class EmailSender {
    private static String emailFrom = System.getenv("EMAIL_FROM");
    private static String emailServer = System.getenv("EMAIL_SERVER");
    private static String emailServerPassword = System.getenv("EMAIL_SERVER_PASSWORD");
    private static String emailServerPort = System.getenv("EMAIL_SERVER_PORT");
    private static String emailTo = System.getenv("EMAIL_TO");

    public void sendEmail(String subject, String emailContent) throws Exception {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", emailServer);
        prop.put("mail.smtp.port", emailServerPort);
        prop.put("mail.smtp.ssl.trust", emailServer);

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailFrom, emailServerPassword);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(emailFrom));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
        message.setSubject(subject);
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(emailContent, "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);
        Transport.send(message);
    }
}
