package com.example.springrestapi.mappers;

import com.example.springrestapi.entities.User;
import com.example.springrestapi.entities.UserRole;

public class UserRoleMapper {
    UserRole toUserRole(User user) {
        return new UserRole();
    }
}
