package com.example.springrestapi.requestBodies;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class OTP {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SendOTP {
        private String email;
        private String phoneNumber;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ValidateOTP {
        private String email;
        private String phoneNumber;

        @Size(min = 6, max = 6)
        private String code;
    }
}
