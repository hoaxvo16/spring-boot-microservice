package com.example.springrestapi.services;

import org.springframework.stereotype.Service;

import com.example.springrestapi.asyncAction.ActionKeys;
import com.example.springrestapi.asyncAction.RunnableStore;
import com.example.springrestapi.messages.QueueMessage;
import com.example.springrestapi.services.interfaces.DelegateService;

@Service("orderDelegateService")
public class OrderDelegateServiceImpl implements DelegateService {

    @Override
    public void receiveAction(String actionType, String domain, QueueMessage message) throws Exception {
        switch (actionType) {
            case "reduceQuantity":
                RunnableStore.runAction(ActionKeys.saveOrderDetail + message.getMessageId());
                break;
            case "checkUserIdOfOrder":
                RunnableStore.runAction(ActionKeys.saveOrder + message.getMessageId());
                break;
            case "error":
                handleError(domain, message);
                break;
        }

    }

    @Override
    public void handleError(String reason, QueueMessage message) throws Exception {
        RunnableStore.log();

        System.out.println("---------");
        switch (reason) {
            case "reduceQuantityItemNotFound":
                RunnableStore.removeAction(ActionKeys.saveOrderDetail + message.getMessageId());
                break;
            case "userIdOfOrderNotFound":
                RunnableStore.removeAction(ActionKeys.saveOrder + message.getMessageId());
                break;
        }
        System.out.println(reason + "  " + message.getMessageId());
        RunnableStore.log();
    }

}
