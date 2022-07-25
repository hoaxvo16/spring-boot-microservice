package com.example.springrestapi.services.interfaces;

import java.util.UUID;

public interface PublisherService {
    UUID sendMessage(Object data, String routingKey) throws Exception;

    void sendMessage(Object data, String routingKey, UUID messageId) throws Exception;
}
