package com.example.springrestapi.services;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.springrestapi.parameters.EmailContent;
import com.example.springrestapi.services.interfaces.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendEmail(EmailContent content) throws Exception {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setFrom(sender);
            helper.setTo(content.getRecipient());
            helper.setText(content.getMsgBody(), true);
            helper.setSubject(content.getSubject());
            mailSender.send(mimeMessage);
            return "Mail Sent Successfully...";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while Sending Mail");
        }
    }

}
