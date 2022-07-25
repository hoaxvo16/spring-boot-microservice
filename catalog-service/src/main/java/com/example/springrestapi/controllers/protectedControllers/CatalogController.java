package com.example.springrestapi.controllers.protectedControllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springrestapi.configurations.RequestConfig;
import com.example.springrestapi.models.CatalogDto;
import com.example.springrestapi.responseBodies.CatalogItemResponse;
import com.example.springrestapi.responseBodies.CatalogResponse;
import com.example.springrestapi.services.interfaces.CatalogService;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(RequestConfig.BASE_PROTECTED_URL + "/catalogs")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping
    public ResponseEntity<List<CatalogResponse>> getCatalogs() throws Exception {

        List<CatalogResponse> response = catalogService.getCatalogs();
        return new ResponseEntity<List<CatalogResponse>>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CatalogResponse> createNewCatalog(@Valid @RequestBody CatalogDto body) throws Exception {

        CatalogResponse response = catalogService.createCatalog(body);
        return new ResponseEntity<CatalogResponse>(response, HttpStatus.OK);
    }

    @GetMapping(value = "{id}/items")
    public ResponseEntity<List<CatalogItemResponse>> getItems(@PathVariable(value = "id") Long id) throws Exception {

        List<CatalogItemResponse> response = catalogService.getCatalogItems(id);
        return new ResponseEntity<List<CatalogItemResponse>>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CatalogResponse> editCatalog(@PathVariable Long id, @RequestBody CatalogDto dto)
            throws Exception {

        CatalogResponse response = catalogService.editCatalog(dto, id);
        return new ResponseEntity<CatalogResponse>(response, HttpStatus.OK);

    }

}
