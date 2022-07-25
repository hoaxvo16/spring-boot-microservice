package com.example.springrestapi.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.springrestapi.enums.Role;
import com.example.springrestapi.requestFilters.JwtTokenValidationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain configureFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/verify/admin").hasRole(Role.ADMIN.toString())
                .antMatchers(RequestConfig.BASE_PUBLIC_URL + "/*").permitAll()
                .and()
                .httpBasic();

        http.addFilterBefore(getTokenValidationFilter(),
                BasicAuthenticationFilter.class);

        http.csrf().disable();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtTokenValidationFilter getTokenValidationFilter() {
        return new JwtTokenValidationFilter("/verify");
    }

}
