package com.example.springrestapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springrestapi.messages.QueueMessage;
import com.example.springrestapi.messages.data.CatalogItemQuantityMessage;
import com.example.springrestapi.services.interfaces.CatalogItemService;
import com.example.springrestapi.services.interfaces.DelegateService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("catalogDelegateService")
public class CatalogDelegateServiceImpl implements DelegateService {

    @Autowired
    private CatalogItemService catalogItemService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void receiveAction(String actionType, String domain, QueueMessage message) throws Exception {
        switch (actionType) {
            case "reduceQuantity":
                CatalogItemQuantityMessage data = objectMapper.convertValue(message.getData(),
                        CatalogItemQuantityMessage.class);
                catalogItemService.reduceQuantity(data.getQuantity(), data.getItemId(), message.getMessageId());
                break;
            case "error":
                handleError(domain, message);
                break;
        }
    }

    @Override
    public void handleError(String reason, QueueMessage message) throws Exception {
        // TODO Auto-generated method stub

    }
}
