package com.example.springrestapi.services.interfaces;

import java.util.UUID;

import com.example.springrestapi.entities.CatalogItem;
import com.example.springrestapi.models.CatalogItemDto;
import com.example.springrestapi.models.EditCatalogItemDto;
import com.example.springrestapi.responseBodies.CatalogItemResponse;

public interface CatalogItemService {
    CatalogItemResponse createCatalogItem(CatalogItemDto dto, Long catalogId) throws Exception;

    CatalogItem getCatalogItemById(Long id) throws Exception;

    CatalogItemResponse editCatalogItem(EditCatalogItemDto dto, Long id, Long catalogId) throws Exception;

    CatalogItem reduceQuantity(int quantity, String itemId, UUID messageId) throws Exception;

    CatalogItem getCatalogItemByItemId(String itemId) throws Exception;

}
