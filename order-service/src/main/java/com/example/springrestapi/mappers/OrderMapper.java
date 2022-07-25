package com.example.springrestapi.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.example.springrestapi.entities.Order;
import com.example.springrestapi.entities.OrderDetail;
import com.example.springrestapi.models.OrderDto;
import com.example.springrestapi.responseBodies.AllOrderResponse;
import com.example.springrestapi.responseBodies.SingleOrderResponse;

@Component
public class OrderMapper {
    public Order toOrder(OrderDto dto) {
        return new Order(dto.getComment(), dto.getStatus(), dto.getAccountId());
    }

    public SingleOrderResponse toSingleOrderResponse(Order order) {

        List<UUID> detailIds = new ArrayList<>();

        List<OrderDetail> orderDetails = order.getOrderDetails();

        for (OrderDetail detail : orderDetails) {
            detailIds.add(detail.getDetailId());
        }

        return new SingleOrderResponse(order.getOrderId(), UUID.randomUUID(), detailIds, order.getCreatedDate(),
                order.getModifiedDate(), order.getComment(), order.getStatus());

    }

    public AllOrderResponse toAllOrderResponse(Order order) {
        return new AllOrderResponse(order.getOrderId(), order.getCreatedDate(), order.getModifiedDate(),
                order.getComment(), order.getStatus());
    }
}
