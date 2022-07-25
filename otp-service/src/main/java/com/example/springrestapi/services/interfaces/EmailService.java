package com.example.springrestapi.services.interfaces;

import com.example.springrestapi.parameters.EmailContent;

public interface EmailService {
    String sendEmail(EmailContent content) throws Exception;
}
