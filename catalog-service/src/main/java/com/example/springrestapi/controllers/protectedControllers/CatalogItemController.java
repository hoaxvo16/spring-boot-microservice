package com.example.springrestapi.controllers.protectedControllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springrestapi.configurations.RequestConfig;
import com.example.springrestapi.models.CatalogItemDto;
import com.example.springrestapi.models.EditCatalogItemDto;
import com.example.springrestapi.responseBodies.CatalogItemResponse;
import com.example.springrestapi.services.interfaces.CatalogItemService;

@RestController
@RequestMapping(RequestConfig.BASE_PROTECTED_URL + "/catalogs/{catalogId}/items")
public class CatalogItemController {

    @Autowired
    private CatalogItemService catalogItemService;

    @PostMapping
    public ResponseEntity<CatalogItemResponse> createNewCatalogItem(@Valid @RequestBody CatalogItemDto body,
            @PathVariable(value = "catalogId") Long catalogId) throws Exception {
                

        CatalogItemResponse response = catalogItemService.createCatalogItem(body, catalogId);
        return new ResponseEntity<CatalogItemResponse>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")

    public ResponseEntity<CatalogItemResponse> editCatalogItem(@Valid @RequestBody EditCatalogItemDto body,
            @PathVariable(value = "catalogId") Long catalogId, @PathVariable(value = "id") Long id) throws Exception {
        CatalogItemResponse response = catalogItemService.editCatalogItem(body, id, catalogId);
        return new ResponseEntity<CatalogItemResponse>(response, HttpStatus.OK);
    }

}
