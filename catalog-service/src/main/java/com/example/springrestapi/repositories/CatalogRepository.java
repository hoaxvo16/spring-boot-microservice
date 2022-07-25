package com.example.springrestapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springrestapi.entities.Catalog;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Long> {
    Optional<Catalog> findByCatalogId(String catalogId);
}
