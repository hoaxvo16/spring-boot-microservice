package com.example.springrestapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.springrestapi.entities.User;
import com.example.springrestapi.models.RegisterUserDto;
import com.example.springrestapi.repositories.UserRepository;
import com.example.springrestapi.requestBodies.Auth.AuthRequest;
import com.example.springrestapi.responseBodies.AuthResponse;
import com.example.springrestapi.securityImpl.SecurityUser;
import com.example.springrestapi.services.interfaces.AuthService;
import com.example.springrestapi.services.interfaces.UserService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenServiceImpl jwtTokenService;

    @Override
    @Transactional
    public AuthResponse login(AuthRequest request) throws Exception {

        User existedUser = userRepository.findByEmail(request.getEmail());
        if (existedUser == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "User with email: " + request.getEmail() + " not existed");
        }

        if (!passwordEncoder.matches(request.getPassword(), existedUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Credential fail");
        }

        String token = jwtTokenService.generateToken(new SecurityUser(existedUser));

        return new AuthResponse(token);
    }

    @Override
    @Transactional
    public AuthResponse registerUser(RegisterUserDto user) throws Exception {

        User existedUser = userRepository.findByEmail(user.getEmail());
        if (existedUser != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "User with email: " + user.getEmail() + " already existed");
        }

        User newUser = userService.addNewUser(user);

        String token = jwtTokenService.generateToken(new SecurityUser(newUser));

        return new AuthResponse(token);
    }

}
