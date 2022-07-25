package com.example.springrestapi.services.interfaces;

import com.example.springrestapi.models.RegisterUserDto;
import com.example.springrestapi.requestBodies.Auth.AuthRequest;
import com.example.springrestapi.responseBodies.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest request) throws Exception;

    AuthResponse registerUser(RegisterUserDto user) throws Exception;

}
