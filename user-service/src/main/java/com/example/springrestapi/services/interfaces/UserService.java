package com.example.springrestapi.services.interfaces;

import java.util.UUID;

import com.example.springrestapi.entities.User;
import com.example.springrestapi.models.UserDto;

public interface UserService {

    UserDto editUser(UserDto user, UUID id) throws Exception;

    User getUserByEmail(String email) throws Exception;

    User getUserByPhoneNumber(String phone) throws Exception;

    User getUserByAccountId(UUID accountId) throws Exception;

    void checkOrderAccountId(UUID accountId, UUID messageId) throws Exception;

}
