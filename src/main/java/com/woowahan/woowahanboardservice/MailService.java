package com.woowahan.woowahanboardservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    @Value("${mail.username}")
    private static String FROM_ADDRESS;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(MailParam mailParam) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailParam.getAddress());
        message.setFrom(MailService.FROM_ADDRESS);
        message.setSubject(mailParam.getTitle());
        message.setText(mailParam.getMessage());

        mailSender.send(message);
    }
}
