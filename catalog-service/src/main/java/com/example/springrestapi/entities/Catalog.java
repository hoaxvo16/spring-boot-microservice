package com.example.springrestapi.entities;

import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Catalog {

    @Id
    @GeneratedValue
    Long id;

    @Column(length = 8, unique = true)
    private String catalogId;

    @Column(length = 64)
    private String catalogName;

    @Column(length = 64)
    private String description;

    @OneToMany(targetEntity = CatalogItem.class, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH })
    @JoinColumn(name = "catalog_id")
    List<CatalogItem> catalogItems;

    @CreatedDate
    private Instant createdDate;

    private Instant modifiedDate;

    public Catalog(String catalogId, String catalogName, String description) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.description = description;
        this.createdDate = Instant.now();
        this.modifiedDate = Instant.now();
    }

}
