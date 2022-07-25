package com.example.springrestapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springrestapi.messages.QueueMessage;
import com.example.springrestapi.messages.data.UserIdMessage;
import com.example.springrestapi.services.interfaces.DelegateService;
import com.example.springrestapi.services.interfaces.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("userDelegateService")
public class UserDelegateServiceImpl implements DelegateService {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void receiveAction(String actionType, String domain, QueueMessage message) throws Exception {
        switch (actionType) {
            case "checkUserIdOfOrder":
                UserIdMessage data = objectMapper.convertValue(message.getData(), UserIdMessage.class);
                userService.checkOrderAccountId(data.getAccountId(), message.getMessageId());
                break;
            case "error":
                handleError(domain, message);
                break;
        }

    }

    @Override
    public void handleError(String reason, QueueMessage message) throws Exception {
        System.out.println(reason);
    }

}
