package com.example.springrestapi.services.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtTokenService {
    String generateToken(UserDetails userDetails);

    Boolean validateToken(String token);

    String getUsernameFromToken(String token);
}
