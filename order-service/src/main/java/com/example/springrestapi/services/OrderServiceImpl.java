package com.example.springrestapi.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.springrestapi.asyncAction.ActionKeys;
import com.example.springrestapi.asyncAction.RunnableStore;
import com.example.springrestapi.entities.Order;
import com.example.springrestapi.mappers.OrderMapper;
import com.example.springrestapi.messages.data.UserIdMessage;
import com.example.springrestapi.models.EditOrderDto;
import com.example.springrestapi.models.OrderDto;
import com.example.springrestapi.repositories.OrderRepository;
import com.example.springrestapi.responseBodies.AllOrderResponse;
import com.example.springrestapi.responseBodies.SingleOrderResponse;
import com.example.springrestapi.services.interfaces.OrderService;
import com.example.springrestapi.services.interfaces.PublisherService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PublisherService publisherService;

    @Override
    @Transactional
    public SingleOrderResponse createOrder(OrderDto dto) throws Exception {

        Order order = orderMapper.toOrder(dto);

        String routingKey = "checkUserIdOfOrder.user.check";

        UUID messageId = publisherService.sendMessage(new UserIdMessage(dto.getAccountId()), routingKey);
        Runnable action = () -> orderRepository.save(order);

        RunnableStore.addAction(ActionKeys.saveOrder + messageId, action);

        return orderMapper.toSingleOrderResponse(order);

    }

    @Override
    public List<AllOrderResponse> getOrders() throws Exception {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(x -> orderMapper.toAllOrderResponse(x)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SingleOrderResponse editOrder(EditOrderDto dto, UUID id) throws Exception {
        Order order = getOrderById(id);

        order.setComment(dto.getComment());
        order.setStatus(dto.getStatus());
        order.setModifiedDate(Instant.now());
        orderRepository.save(order);

        return orderMapper.toSingleOrderResponse(order);
    }

    @Override
    public Order getOrderById(UUID id) {
        Optional<Order> order = orderRepository.findById(id);

        if (order.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find order with id: " + id);
        }

        return order.get();
    }

}
