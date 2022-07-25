package com.example.springrestapi.services.interfaces;

import com.example.springrestapi.messages.QueueMessage;

public interface ConsumerService {
    void listen(QueueMessage message);
}
