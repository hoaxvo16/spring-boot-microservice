package com.example.springrestapi.services.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.springrestapi.entities.User;
import com.example.springrestapi.models.RegisterUserDto;

public interface UserService extends UserDetailsService {

    User addNewUser(RegisterUserDto user) throws Exception;

}
