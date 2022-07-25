package com.example.springrestapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springrestapi.entities.UserOTP;

@Repository
public interface UserOTPRepository extends JpaRepository<UserOTP, Long> {
    UserOTP findByUserEmail(String email);

    UserOTP findByUserPhoneNumber(String phoneNumber);
}
