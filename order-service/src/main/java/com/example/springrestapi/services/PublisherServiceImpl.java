package com.example.springrestapi.services;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springrestapi.configurations.RabbitMQConfig;
import com.example.springrestapi.messages.QueueMessage;
import com.example.springrestapi.services.interfaces.PublisherService;

@Service
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public UUID sendMessage(Object data, String routingKey) throws Exception {
        QueueMessage message = new QueueMessage(data, routingKey, RabbitMQConfig.TOPIC_EXCHANGE,
                RabbitMQConfig.QUEUE_NAME);
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, routingKey, message);
        return message.getMessageId();
    }
}
