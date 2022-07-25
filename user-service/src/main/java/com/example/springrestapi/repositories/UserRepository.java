package com.example.springrestapi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springrestapi.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);

    User findByPhone(String phone);
}