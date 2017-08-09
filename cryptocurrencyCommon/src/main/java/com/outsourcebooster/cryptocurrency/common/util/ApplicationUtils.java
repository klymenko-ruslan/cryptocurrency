package com.outsourcebooster.cryptocurrency.common.util;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by rklimemnko on 03.09.2016.
 */
public class ApplicationUtils {

    public static String getCommonProperty(String propertyName) {
        Config cfg = new Config();
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
        Map<String, Object> commonPropertiesMap = instance.getMap("commonProperties");
        return (String)commonPropertiesMap.get(propertyName);
    }

    public static Map<String, Object> getCommonPropertiesMap() {
        Config cfg = new Config();
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
        return instance.getMap("commonProperties");
    }

    private final static String username = "cryptocurrencyapp@gmail.com";
    private final static  String password = "Crypto123654~";
    private final static Properties props = new Properties(){{
        put("mail.smtp.auth", "true");
        put("mail.smtp.starttls.enable", "true");
        put("mail.smtp.host", "smtp.gmail.com");
        put("mail.smtp.port", "587");
    }};

    private final static Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

    public static void sendEmail(String subject, String text, String reciever) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(reciever));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
