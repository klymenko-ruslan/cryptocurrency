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

    private static String hazelcastInstanceName = "instance";
    private static String commonPropertiesMap = "commonProperties";

    public static Config cfg;
    static {
        cfg = new Config();
        cfg.getNetworkConfig().setPort(5701);
        cfg.getNetworkConfig().setPortCount(20);
        cfg.getNetworkConfig().setPortAutoIncrement(true);
        cfg.setInstanceName(hazelcastInstanceName);
    }


    public static String getCommonProperty(String propertyName) {
        HazelcastInstance instance = Hazelcast.getOrCreateHazelcastInstance(cfg);
        Map<String, String> commonPropertiesMap = instance.getMap(ApplicationUtils.commonPropertiesMap);
        return commonPropertiesMap.get(propertyName);
    }

    public static Map<String, Object> getCommonPropertiesMap() {
        return Hazelcast.getOrCreateHazelcastInstance(cfg).getMap(commonPropertiesMap);
    }

    private final static String email = "cryptocurrencyapp@gmail.com";
    private final static  String password = "Crypto123654~";
    private final static Properties props = new Properties(){{
        put("mail.smtp.auth", "true");
        put("mail.smtp.starttls.enable", "true");
        put("mail.smtp.host", "smtp.gmail.com");
        put("mail.smtp.port", "587");
    }};


    private static Session getSession() {
        return Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, password);
                    }
                });
    }
    public static void sendEmail(String subject, String text, String reciever) {
        try {
            Message message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress(email));
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
