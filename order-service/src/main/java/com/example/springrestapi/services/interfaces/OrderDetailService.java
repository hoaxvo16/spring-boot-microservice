package com.example.springrestapi.services.interfaces;

import java.util.UUID;

import com.example.springrestapi.models.EditOrderDetailDto;
import com.example.springrestapi.models.OrderDetailDto;
import com.example.springrestapi.responseBodies.OrderDetailResponse;

public interface OrderDetailService {
    OrderDetailResponse createOrderDetail(OrderDetailDto dto, UUID orderId) throws Exception;

    OrderDetailResponse editOrderDetail(EditOrderDetailDto dto, UUID orderId, UUID orderDetailId) throws Exception;
}
