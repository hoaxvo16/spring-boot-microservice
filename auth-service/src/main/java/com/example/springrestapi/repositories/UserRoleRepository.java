package com.example.springrestapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springrestapi.entities.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}
