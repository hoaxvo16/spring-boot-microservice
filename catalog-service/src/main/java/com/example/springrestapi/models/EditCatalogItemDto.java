package com.example.springrestapi.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditCatalogItemDto {
    @NotBlank
    @Size(max = 8)
    private String itemId;

    @Size(max = 64)
    @NotBlank
    private String itemName;

    @Size(max = 64)
    private String description;

    @Size(max = 8)

    private String catalogId;

    private int quantity;
}
