package com.example.springrestapi.responseBodies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogResponse {

    private Long id;

    private String catalogId;

    private String catalogName;

    private String description;

}
