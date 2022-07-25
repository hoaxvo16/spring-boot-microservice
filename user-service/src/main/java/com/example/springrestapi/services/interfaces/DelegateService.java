package com.example.springrestapi.services.interfaces;

import com.example.springrestapi.messages.QueueMessage;

public interface DelegateService {
    void receiveAction(String actionType, String domain, QueueMessage message) throws Exception;

    void handleError(String reason, QueueMessage message) throws Exception;
}
