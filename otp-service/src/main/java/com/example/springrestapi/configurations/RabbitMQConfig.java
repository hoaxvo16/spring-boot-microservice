package com.example.springrestapi.configurations;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "VERIFY_QUEUE";

    public static final String TOPIC_EXCHANGE = "TOPIC_EXCHANGE";

    public static final String ROUTING_KEY = "*.verify.*";

    @Bean
    public Queue getQueue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public TopicExchange getTopicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    Binding bindingTopicExchangeToQueue2(TopicExchange exchange) {
        return BindingBuilder.bind(getQueue()).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
