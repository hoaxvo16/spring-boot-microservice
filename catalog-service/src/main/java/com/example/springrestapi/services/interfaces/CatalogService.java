package com.example.springrestapi.services.interfaces;

import java.util.List;

import com.example.springrestapi.entities.Catalog;
import com.example.springrestapi.models.CatalogDto;
import com.example.springrestapi.responseBodies.CatalogItemResponse;
import com.example.springrestapi.responseBodies.CatalogResponse;

public interface CatalogService {
    CatalogResponse createCatalog(CatalogDto dto) throws Exception;

    List<CatalogItemResponse> getCatalogItems(Long id) throws Exception;

    public List<CatalogResponse> getCatalogs() throws Exception;

    Catalog getCatalogById(Long id) throws Exception;

    CatalogResponse editCatalog(CatalogDto dto, Long id) throws Exception;

    Catalog getCatalogByCatalogId(String catalogId) throws Exception;
}
