package com.example.springrestapi.controllers.publicControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springrestapi.configurations.RequestConfig;
import com.example.springrestapi.parameters.EmailContent;
import com.example.springrestapi.services.interfaces.EmailService;

@RestController
@RequestMapping(value = RequestConfig.BASE_PUBLIC_URL + "/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody EmailContent content) throws Exception {
        String result = emailService.sendEmail(content);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }
}
