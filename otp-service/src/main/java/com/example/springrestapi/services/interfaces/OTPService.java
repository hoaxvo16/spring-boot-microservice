package com.example.springrestapi.services.interfaces;

import javax.validation.constraints.Email;

import org.springframework.validation.annotation.Validated;

@Validated
public interface OTPService {
    String generateOTP();

    String sendOTPToEmail(@Email String email) throws Exception;

    String sendOTPToPhone(String phoneNumber) throws Exception;

    String verifyOTPFromEmail(@Email String email, String code) throws Exception;

    String verifyOTPFromPhone(String phoneNumber, String code) throws Exception;
}
