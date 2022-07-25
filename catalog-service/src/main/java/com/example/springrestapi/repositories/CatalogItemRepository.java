package com.example.springrestapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springrestapi.entities.CatalogItem;

@Repository
public interface CatalogItemRepository extends JpaRepository<CatalogItem, Long> {
    Optional<CatalogItem> findByItemId(String itemId);
}
