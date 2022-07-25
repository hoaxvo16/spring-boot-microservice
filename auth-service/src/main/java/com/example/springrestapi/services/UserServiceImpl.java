package com.example.springrestapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springrestapi.entities.User;
import com.example.springrestapi.entities.UserRole;
import com.example.springrestapi.mappers.UserMapper;
import com.example.springrestapi.models.RegisterUserDto;
import com.example.springrestapi.repositories.UserRepository;
import com.example.springrestapi.repositories.UserRoleRepository;
import com.example.springrestapi.securityImpl.SecurityUser;
import com.example.springrestapi.services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public User addNewUser(RegisterUserDto user) throws Exception {
        User newUser = userMapper.toUser(user);
        List<UserRole> roles = userRoleRepository.findAll();
        newUser.setRoles(roles);
        userRepository.save(newUser);
        return newUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with " + username + " not found");
        }
        return new SecurityUser(user);
    }

}
