package com.example.springrestapi.messages;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueueMessage implements Serializable {
    private Object data;

    private String routingKey;

    private String topicName;

    private String queueName;

    private UUID messageId;

    public QueueMessage(Object data, String routingKey, String topicName, String queueName) {
        this.data = data;
        this.routingKey = routingKey;
        this.topicName = topicName;
        this.queueName = queueName;
        this.messageId = UUID.randomUUID();
    }

}
