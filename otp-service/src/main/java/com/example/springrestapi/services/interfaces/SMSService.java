package com.example.springrestapi.services.interfaces;

public interface SMSService {
    void sendSMS(String content, String phoneNumber) throws Exception;
}
