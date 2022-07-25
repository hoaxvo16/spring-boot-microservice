package com.example.springrestapi.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springrestapi.configurations.RabbitMQConfig;

@Component
public class VerifyPublisher {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendMessage(Object message, String routingKey) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, routingKey, message);
    }

}
