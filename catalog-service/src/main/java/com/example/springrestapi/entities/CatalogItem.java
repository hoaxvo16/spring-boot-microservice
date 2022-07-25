package com.example.springrestapi.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class CatalogItem implements Serializable {

    @Id
    @GeneratedValue
    Long id;

    @Column(length = 8, unique = true)
    private String itemId;

    @Column(length = 64)
    private String itemName;

    @Column(length = 64)
    private String description;

    @ManyToOne
    @JoinColumn(name = "catalog_id")
    private Catalog catalog;

    @CreatedDate
    private Instant createdDate;

    private Instant modifiedDate;

    private int quantity;

    public CatalogItem(String id, String name, String description, Catalog catalog, int quantity) {
        this.itemId = id;
        this.itemName = name;
        this.description = description;
        this.createdDate = Instant.now();
        this.modifiedDate = Instant.now();
        this.catalog = catalog;
        this.quantity = quantity;
    }

}
