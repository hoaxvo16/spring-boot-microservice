package com.example.springrestapi.mappers;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.example.springrestapi.entities.Order;
import com.example.springrestapi.entities.OrderDetail;
import com.example.springrestapi.models.OrderDetailDto;
import com.example.springrestapi.responseBodies.OrderDetailResponse;

@Component
public class OrderDetailMapper {
    public OrderDetail toOrderDetail(OrderDetailDto dto, Order order) {
        return new OrderDetail(order, dto.getQuantity(), dto.getStatus(), dto.getItemId());
    }

    public OrderDetailResponse toOrderDetailResponse(OrderDetail orderDetail) {

        UUID orderId = orderDetail.getOrder().getOrderId();

        return new OrderDetailResponse(orderDetail.getDetailId(), orderId, orderDetail.getCreatedDate(),
                orderDetail.getModifiedDate(), orderDetail.getQuantity(), orderDetail.getStatus());
    }
}
