package com.example.springrestapi.controllers.publicControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springrestapi.configurations.RequestConfig;
import com.example.springrestapi.services.interfaces.SMSService;

@RestController
@RequestMapping(value = RequestConfig.BASE_PUBLIC_URL + "/sms")
public class SMSController {
    @Autowired
    SMSService smsService;

    @PostMapping
    public void sendSMS() {

    }
}
