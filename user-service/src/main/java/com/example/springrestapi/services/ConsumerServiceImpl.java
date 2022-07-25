package com.example.springrestapi.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.springrestapi.configurations.RabbitMQConfig;
import com.example.springrestapi.messages.QueueMessage;
import com.example.springrestapi.services.interfaces.ConsumerService;
import com.example.springrestapi.services.interfaces.DelegateService;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    @Qualifier("userDelegateService")
    private DelegateService userDelegateService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    @Override
    public void listen(QueueMessage message) {

        try {
            String routingKey = message.getRoutingKey();
            String[] splitStrings = routingKey.split("\\.");
            switch (splitStrings[1]) {
                case "user":
                    userDelegateService.receiveAction(splitStrings[0], splitStrings[2], message);
                    break;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
