package com.example.springrestapi.requestFilters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import com.example.springrestapi.services.interfaces.JwtTokenService;
import com.example.springrestapi.services.interfaces.UserService;

public class JwtTokenValidationFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenService jwtTokenService;

    @Autowired
    UserService userService;

    private final String filterUrl;

    public JwtTokenValidationFilter(String filterUrl) {
        this.filterUrl = filterUrl;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, ResponseStatusException {

        try {
            String token = getJwtToken(request);
            if (!jwtTokenService.validateToken(token)) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            }
            String email = jwtTokenService.getUsernameFromToken(token);
            UserDetails userDetails = userService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        if (request.getRequestURI().startsWith(filterUrl))
            return false;
        return true;
    }

    protected String getJwtToken(HttpServletRequest request) {

        try {
            String bearerToken = request.getHeader("Authorization");
            if (bearerToken.startsWith("Bearer ")) {
                return bearerToken.substring(7);
            }
            return null;
        } catch (Exception ex) {
            return null;
        }
    }

}
