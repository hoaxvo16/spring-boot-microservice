package com.example.springrestapi.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.springrestapi.entities.UserOTP;
import com.example.springrestapi.parameters.EmailContent;
import com.example.springrestapi.repositories.UserOTPRepository;
import com.example.springrestapi.services.interfaces.EmailService;
import com.example.springrestapi.services.interfaces.OTPService;
import com.example.springrestapi.services.interfaces.SMSService;

@Service
public class OTPServiceImpl implements OTPService {

    private final String message = "Your OTP code is: ";

    private final String subject = "Verify OTP";

    @Autowired
    private EmailService emailService;

    @Autowired
    SMSService smsService;

    @Autowired
    private UserOTPRepository userOTPRepository;

    @Override
    public String generateOTP() {
        String otp = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            otp += random.nextInt(10);
        }
        return otp;
    }

    @Override
    @Transactional
    public String sendOTPToEmail(@Email String email) throws Exception {

        UserOTP existedUserOTP = userOTPRepository.findByUserEmail(email);
        String code = generateOTP();
        EmailContent content = new EmailContent(email, message + "<b>" + code + "</b>",
                subject);
        String result = emailService.sendEmail(content);
        if (existedUserOTP == null) {
            UserOTP userOTP = new UserOTP(code, email, null);
            userOTPRepository.save(userOTP);
            return result;
        }

        existedUserOTP.setCode(code);
        existedUserOTP.setCreatedDate(Instant.now());
        userOTPRepository.save(existedUserOTP);
        return result;
    }

    @Override
    @Transactional
    public String sendOTPToPhone(String phoneNumber) throws Exception {

        UserOTP existedUserOTP = userOTPRepository.findByUserPhoneNumber(phoneNumber);
        String code = generateOTP();
        String content = message + code;
        smsService.sendSMS(content, phoneNumber);

        if (existedUserOTP == null) {
            UserOTP userOTP = new UserOTP(code, null, phoneNumber);
            userOTPRepository.save(userOTP);
            return "SMS sent";
        }

        existedUserOTP.setCode(code);
        existedUserOTP.setCreatedDate(Instant.now());
        userOTPRepository.save(existedUserOTP);

        return "SMS sent";
    }

    @Override
    public String verifyOTPFromEmail(String email, String code) throws Exception {
        UserOTP userOTP = userOTPRepository.findByUserEmail(email);
        String result = verify(userOTP, code);
        return result;
    }

    @Override
    public String verifyOTPFromPhone(String phoneNumber, String code) throws Exception {
        UserOTP userOTP = userOTPRepository.findByUserPhoneNumber(phoneNumber);
        String result = verify(userOTP, code);
        return result;
    }

    private String verify(UserOTP userOTP, String code) {
        Instant createdOTPTime = userOTP.getCreatedDate();
        Instant now = Instant.now();

        if (ChronoUnit.MINUTES.between(createdOTPTime, now) > 5) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "OTP verify time is over!!!");
        }

        if (!userOTP.getCode().equals(code)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "OTP is not correct!!!");
        }

        return "OTP verified !!!";
    }

}
