package com.example.springrestapi.services;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.example.springrestapi.entities.Catalog;
import com.example.springrestapi.entities.CatalogItem;
import com.example.springrestapi.mappers.CatalogItemMapper;
import com.example.springrestapi.models.CatalogItemDto;
import com.example.springrestapi.models.EditCatalogItemDto;
import com.example.springrestapi.repositories.CatalogItemRepository;
import com.example.springrestapi.repositories.CatalogRepository;
import com.example.springrestapi.responseBodies.CatalogItemResponse;
import com.example.springrestapi.services.interfaces.CatalogItemService;
import com.example.springrestapi.services.interfaces.CatalogService;
import com.example.springrestapi.services.interfaces.PublisherService;

@Service
public class CatalogItemServiceImpl implements CatalogItemService {

    @Autowired
    private CatalogItemRepository catalogItemRepository;

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private CatalogItemMapper catalogItemMapper;

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private PublisherService publisherService;

    @Override
    @Transactional
    public CatalogItemResponse createCatalogItem(CatalogItemDto dto, Long id) throws Exception {
        Optional<Catalog> catalog = catalogRepository.findById(id);

        if (catalog.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Catalog with id:  " + id + " does not exist");
        }

        if (!catalogItemRepository.findByItemId(dto.getItemId()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Catalog item with id:  " + dto.getItemId() + " already exist");
        }
        CatalogItem map = catalogItemMapper.toCatalogItem(dto, catalog.get());
        catalogItemRepository.save(map);
        return catalogItemMapper.toCatalogItemResponse(map);
    }

    @Override
    public CatalogItem getCatalogItemById(Long id) throws Exception {

        CatalogItem item = catalogItemRepository.getReferenceById(id);
        if (item == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Catalog item with id:  " + id + " is not exist");
        }
        return item;
    }

    @Override
    @Transactional
    public CatalogItemResponse editCatalogItem(EditCatalogItemDto dto, Long id, Long catalogId) throws Exception {
        catalogService.getCatalogById(catalogId);
        CatalogItem catalogItem = getCatalogItemById(id);
        Optional<CatalogItem> existedItemToEdit = catalogItemRepository.findByItemId(dto.getItemId());
        if (!existedItemToEdit.isEmpty() && catalogItem.getItemId() != existedItemToEdit.get().getItemId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Catalog item with id:  " + dto.getItemId() + " is traded");
        }

        if (!catalogItem.getCatalog().getId().equals(catalogId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Catalog with id: " + catalogId + " does not have any item with id: " + id);
        }

        Catalog newCatalog = catalogService.getCatalogByCatalogId(dto.getCatalogId());

        catalogItem.setCatalog(newCatalog);
        catalogItem.setDescription(dto.getDescription());
        catalogItem.setItemId(dto.getItemId());
        catalogItem.setItemName(dto.getItemName());
        catalogItem.setModifiedDate(Instant.now());
        catalogItem.setQuantity(dto.getQuantity());
        catalogItemRepository.save(catalogItem);

        return catalogItemMapper.toCatalogItemResponse(catalogItem);
    }

    @Override
    public CatalogItem reduceQuantity(int quantity, String itemId, UUID messageId) throws Exception {
        System.out.println(itemId);
        Optional<CatalogItem> find = catalogItemRepository.findByItemId(itemId);
        if (find.isEmpty()) {
            String routingKey = "error.order.reduceQuantityItemNotFound";

            publisherService.sendMessage(itemId, routingKey, messageId);
        }
        CatalogItem catalogItem = find.get();
        int newQuantity = catalogItem.getQuantity() - quantity;
        if (newQuantity < 0) {
            String routingKey = "error.order.itemNotHaveEnoughQuantity";
            publisherService.sendMessage(itemId, routingKey, messageId);
        }
        catalogItem.setQuantity(newQuantity);
        catalogItemRepository.save(catalogItem);
        String routingKey = "reduceQuantity.order.success";
        publisherService.sendMessage(itemId, routingKey, messageId);
        return catalogItem;
    }

    @Override
    public CatalogItem getCatalogItemByItemId(String itemId) throws Exception {
        Optional<CatalogItem> catalogItem = catalogItemRepository.findByItemId(itemId);
        if (catalogItem.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item with item id: " + itemId + " not found");
        }
        return catalogItem.get();
    }

}
