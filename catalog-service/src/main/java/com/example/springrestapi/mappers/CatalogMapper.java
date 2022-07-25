package com.example.springrestapi.mappers;

import org.springframework.stereotype.Component;

import com.example.springrestapi.entities.Catalog;
import com.example.springrestapi.models.CatalogDto;
import com.example.springrestapi.responseBodies.CatalogResponse;

@Component
public class CatalogMapper {
    public Catalog toCatalog(CatalogDto dto) {
        return new Catalog(dto.getCatalogId(), dto.getCatalogName(), dto.getDescription());
    }

    public CatalogDto toCatalogDto(Catalog catalog) {
        return new CatalogDto(catalog.getCatalogId(), catalog.getCatalogName(), catalog.getDescription());
    }

    public CatalogResponse toCatalogResponse(Catalog catalog) {
        return new CatalogResponse(catalog.getId(), catalog.getCatalogId(), catalog.getCatalogName(),
                catalog.getDescription());
    }
}
