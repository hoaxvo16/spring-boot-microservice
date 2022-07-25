package com.example.springrestapi.controllers.publicControllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springrestapi.configurations.RequestConfig;
import com.example.springrestapi.models.RegisterUserDto;
import com.example.springrestapi.requestBodies.Auth.AuthRequest;
import com.example.springrestapi.responseBodies.AuthResponse;
import com.example.springrestapi.services.interfaces.AuthService;

@RestController
@RequestMapping(RequestConfig.BASE_PUBLIC_URL + "/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> generateOTP(@Valid @RequestBody AuthRequest body) throws Exception {
        AuthResponse result = authService.login(body);

        return new ResponseEntity<AuthResponse>(result, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@Valid @RequestBody RegisterUserDto body) throws Exception {
        AuthResponse res = authService.registerUser(body);
        return new ResponseEntity<AuthResponse>(res, HttpStatus.OK);
    }
}
