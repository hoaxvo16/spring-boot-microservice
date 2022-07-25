package com.example.springrestapi.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springrestapi.entities.Catalog;
import com.example.springrestapi.entities.CatalogItem;
import com.example.springrestapi.models.CatalogItemDto;
import com.example.springrestapi.responseBodies.CatalogItemResponse;

@Component
public class CatalogItemMapper {

    @Autowired
    private CatalogMapper catalogMapper;

    public CatalogItem toCatalogItem(CatalogItemDto dto, Catalog catalog) {
        return new CatalogItem(dto.getItemId(), dto.getItemName(), dto.getDescription(), catalog, dto.getQuantity());
    }

    public CatalogItemDto toCatalogItemDto(CatalogItem catalogItem) {
        return new CatalogItemDto(catalogItem.getItemId(), catalogItem.getItemName(), catalogItem.getDescription(),
                catalogItem.getQuantity());
    }

    public CatalogItemResponse toCatalogItemResponse(CatalogItem catalogItem) {
        return new CatalogItemResponse(catalogItem.getId(), catalogItem.getItemId(), catalogItem.getItemName(),
                catalogItem.getDescription(), catalogMapper.toCatalogResponse(catalogItem.getCatalog()),
                catalogItem.getQuantity());
    }

}
