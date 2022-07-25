package com.example.springrestapi.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.springrestapi.entities.User;
import com.example.springrestapi.models.RegisterUserDto;

@Component
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User toUser(RegisterUserDto userDto) {
        User newUser = new User(userDto.getLastName(), userDto.getEmail(), userDto.getPhone(), userDto.getFirstName());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return newUser;
    }

}
