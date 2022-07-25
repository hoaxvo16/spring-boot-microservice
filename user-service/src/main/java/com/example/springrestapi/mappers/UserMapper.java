package com.example.springrestapi.mappers;

import org.springframework.stereotype.Component;

import com.example.springrestapi.entities.User;
import com.example.springrestapi.models.UserDto;

@Component
public class UserMapper {

    public User toUser(UserDto userDto) {
        return new User(userDto.getLastName(), userDto.getEmail(), userDto.getPhone(), userDto.getFirstName());
    }

}
