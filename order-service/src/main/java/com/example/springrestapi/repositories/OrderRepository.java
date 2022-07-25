package com.example.springrestapi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springrestapi.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

}
