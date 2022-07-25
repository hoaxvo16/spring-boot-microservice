package com.example.springrestapi.responseBodies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogItemResponse {

    private Long id;

    private String itemId;

    private String itemName;

    private String description;

    private CatalogResponse catalog;

    private int quantity;
}
