package com.example.springrestapi.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.springrestapi.services.interfaces.SMSService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SMSServiceImpl implements SMSService {

    @Value(value = "${spring.twilio.account_sid}")
    private String ACCOUNT_SID;

    @Value(value = "${spring.twilio.auth.token}")
    private String AUTH_TOKEN;

    @Value(value = "${spring.twilio.number}")
    private String TWILIO_NUMBER;

    @Override
    public void sendSMS(String content, String phoneNumber) throws Exception {

        try {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message.creator(
                    new PhoneNumber(phoneNumber),
                    new PhoneNumber(TWILIO_NUMBER), content)
                    .create();
        } catch (Exception ex) {
            throw ex;
        }
    }
}
