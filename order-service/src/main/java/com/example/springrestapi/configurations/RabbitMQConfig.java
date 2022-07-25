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

    public static final String QUEUE_NAME = "MY_QUEUE";

    public static final String TOPIC_EXCHANGE = "TOPIC_EXCHANGE";

    public static final String USER_ROUTING_KEY = "*.user.*";

    public static final String ORDER_ROUTING_KEY = "*.order.*";

    public static final String CATALOG_ROUTING_KEY = "*.catalog.*";

    @Bean
    public Queue getQueue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public TopicExchange getTopicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    Binding bindingTopicExchangeToQueueWithUser(TopicExchange exchange) {
        return BindingBuilder.bind(getQueue()).to(exchange).with(USER_ROUTING_KEY);
    }

    @Bean
    Binding bindingTopicExchangeToQueueWithOrder(TopicExchange exchange) {
        return BindingBuilder.bind(getQueue()).to(exchange).with(ORDER_ROUTING_KEY);
    }

    @Bean
    Binding bindingTopicExchangeToQueueWithCatalog(TopicExchange exchange) {
        return BindingBuilder.bind(getQueue()).to(exchange).with(CATALOG_ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
