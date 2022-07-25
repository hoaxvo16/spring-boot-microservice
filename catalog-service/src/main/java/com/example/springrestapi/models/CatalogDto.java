package com.example.springrestapi.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogDto {
    @NotBlank
    @Size(max = 8)
    private String catalogId;

    @NotBlank
    @Size(max = 64)
    private String catalogName;

    @Size(max = 64)
    private String description;
}
