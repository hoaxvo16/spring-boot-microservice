package com.example.springrestapi.services.interfaces;

import java.util.UUID;

public interface PublisherService {
    UUID sendMessage(Object data, String routingKey) throws Exception;
}
