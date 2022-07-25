package com.example.springrestapi.services;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.springrestapi.asyncAction.ActionKeys;
import com.example.springrestapi.asyncAction.RunnableStore;
import com.example.springrestapi.entities.Order;
import com.example.springrestapi.entities.OrderDetail;
import com.example.springrestapi.mappers.OrderDetailMapper;
import com.example.springrestapi.messages.data.CatalogItemQuantityMessage;
import com.example.springrestapi.models.EditOrderDetailDto;
import com.example.springrestapi.models.OrderDetailDto;
import com.example.springrestapi.repositories.OrderDetailRepository;
import com.example.springrestapi.responseBodies.OrderDetailResponse;
import com.example.springrestapi.services.interfaces.OrderDetailService;
import com.example.springrestapi.services.interfaces.OrderService;
import com.example.springrestapi.services.interfaces.PublisherService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private PublisherService publisherService;

    @Override
    @Transactional
    public OrderDetailResponse createOrderDetail(OrderDetailDto dto, UUID orderId) throws Exception {
        Order order = orderService.getOrderById(orderId);
        OrderDetail orderDetail = orderDetailMapper.toOrderDetail(dto, order);

        CatalogItemQuantityMessage messageData = new CatalogItemQuantityMessage(dto.getItemId(), dto.getQuantity());
        String routingKey = "reduceQuantity.catalog.createOrderDetail";
        UUID messageId = publisherService.sendMessage(messageData, routingKey);
        Runnable action = () -> orderDetailRepository.save(orderDetail);
        RunnableStore.addAction(ActionKeys.saveOrderDetail + messageId, action);
        return orderDetailMapper.toOrderDetailResponse(orderDetail);
    }

    @Override
    public OrderDetailResponse editOrderDetail(EditOrderDetailDto dto, UUID orderId, UUID orderDetailId)
            throws Exception {
        orderService.getOrderById(orderId);

        OrderDetail orderDetail = orderDetailRepository.findByOrderDetailIdAndOrderId(orderDetailId, orderId);

        if (orderDetail == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Cannot find order detail with id " + orderDetailId + " in given order id");
        }

        orderDetail.setStatus(dto.getStatus());
        orderDetail.setModifiedDate(Instant.now());

        orderDetailRepository.save(orderDetail);

        return orderDetailMapper.toOrderDetailResponse(orderDetail);
    }

}
