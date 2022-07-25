package com.example.springrestapi.controllers.protectedControllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springrestapi.configurations.RequestConfig;
import com.example.springrestapi.requestBodies.OTP.SendOTP;
import com.example.springrestapi.requestBodies.OTP.ValidateOTP;
import com.example.springrestapi.services.interfaces.OTPService;

@RestController
@RequestMapping(value = RequestConfig.BASE_PROTECTED_URL + "/otp")
public class OTPController {

    @Autowired
    OTPService otpService;

    @PostMapping(value = "/send")
    public ResponseEntity<Void> sendOtp(@RequestBody SendOTP body) throws Exception {

        if (body.getEmail() == null) {
            otpService.sendOTPToPhone(body.getPhoneNumber());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        otpService.sendOTPToEmail(body.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping(value = "/validate")
    public ResponseEntity<Void> validateOtp(@Valid @RequestBody ValidateOTP body) throws Exception {
        if (body.getEmail() == null) {
            otpService.verifyOTPFromPhone(body.getPhoneNumber(), body.getCode());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        otpService.verifyOTPFromEmail(body.getEmail(), body.getCode());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
