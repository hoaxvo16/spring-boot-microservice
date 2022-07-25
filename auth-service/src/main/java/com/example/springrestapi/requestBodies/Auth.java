package com.example.springrestapi.requestBodies;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Auth {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthRequest {
        @Email
        private String email;

        @Size(min = 8, max = 32)
        private String password;
    }

}
