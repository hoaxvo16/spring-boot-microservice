package com.example.springrestapi.entities;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class UserOTP {
    @Id
    @GeneratedValue
    private Long id;

    private String code;

    private String userEmail;

    private String userPhoneNumber;

    @CreatedDate
    Instant createdDate;

    public UserOTP(String code, String userEmail, String phoneNumber) {
        this.code = code;
        this.userEmail = userEmail;
        this.userPhoneNumber = phoneNumber;
        this.createdDate = Instant.now();
    }

}
