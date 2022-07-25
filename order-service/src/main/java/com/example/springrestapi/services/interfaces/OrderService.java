package com.example.springrestapi.services.interfaces;

import java.util.List;
import java.util.UUID;

import com.example.springrestapi.entities.Order;
import com.example.springrestapi.models.EditOrderDto;
import com.example.springrestapi.models.OrderDto;
import com.example.springrestapi.responseBodies.SingleOrderResponse;
import com.example.springrestapi.responseBodies.AllOrderResponse;

public interface OrderService {
    SingleOrderResponse createOrder(OrderDto dto) throws Exception;

    List<AllOrderResponse> getOrders() throws Exception;

    SingleOrderResponse editOrder(EditOrderDto dto, UUID id) throws Exception;

    Order getOrderById(UUID id);

}
