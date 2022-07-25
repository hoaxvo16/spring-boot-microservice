package com.example.springrestapi.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.springrestapi.entities.Catalog;
import com.example.springrestapi.mappers.CatalogItemMapper;
import com.example.springrestapi.mappers.CatalogMapper;
import com.example.springrestapi.models.CatalogDto;
import com.example.springrestapi.repositories.CatalogRepository;
import com.example.springrestapi.responseBodies.CatalogItemResponse;
import com.example.springrestapi.responseBodies.CatalogResponse;
import com.example.springrestapi.services.interfaces.CatalogService;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private CatalogMapper catalogMapper;

    @Autowired
    private CatalogItemMapper catalogItemMapper;

    @Override
    @Transactional
    public CatalogResponse createCatalog(CatalogDto dto) throws Exception {
        Optional<Catalog> catalog = catalogRepository.findByCatalogId(dto.getCatalogId());
        if (!catalog.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Catalog with id: " + dto.getCatalogId() + "  existed");
        }
        Catalog map = catalogMapper.toCatalog(dto);
        catalogRepository.save(map);
        return catalogMapper.toCatalogResponse(map);
    }

    @Override
    public List<CatalogItemResponse> getCatalogItems(Long catalogId) throws Exception {

        Catalog catalog = getCatalogById(catalogId);
        return catalog.getCatalogItems().stream().map(x -> catalogItemMapper.toCatalogItemResponse(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<CatalogResponse> getCatalogs() throws Exception {
        return catalogRepository.findAll().stream().map(x -> catalogMapper.toCatalogResponse(x))
                .collect(Collectors.toList());
    }

    @Override
    public Catalog getCatalogById(Long id) throws Exception {
        Optional<Catalog> catalog = catalogRepository.findById(id);
        if (catalog.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Catalog with id: " + id + "  does not exist");
        }
        return catalog.get();
    }

    @Override
    @Transactional
    public CatalogResponse editCatalog(CatalogDto dto, Long id) throws Exception {
        Catalog catalog = getCatalogById(id);
        Optional<Catalog> existedEditCatalog = catalogRepository.findByCatalogId(dto.getCatalogId());

        if (!existedEditCatalog.isEmpty() && catalog.getCatalogId() != existedEditCatalog.get().getCatalogId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Catalog with id: " + dto.getCatalogId() + "  is traded");
        }

        catalog.setCatalogId(dto.getCatalogId());
        catalog.setCatalogName(dto.getCatalogName());
        catalog.setDescription(dto.getDescription());
        catalog.setModifiedDate(Instant.now());

        catalogRepository.save(catalog);
        return catalogMapper.toCatalogResponse(catalog);

    }

    @Override
    public Catalog getCatalogByCatalogId(String catalogId) throws Exception {
        Optional<Catalog> existedCatalog = catalogRepository.findByCatalogId(catalogId);
        if (existedCatalog.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Catalog with catalogId: " + catalogId + "  does not exist");
        }

        return existedCatalog.get();

    }

}
