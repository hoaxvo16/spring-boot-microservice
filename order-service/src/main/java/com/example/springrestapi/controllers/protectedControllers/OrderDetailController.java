package com.example.springrestapi.controllers.protectedControllers;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springrestapi.configurations.RequestConfig;
import com.example.springrestapi.models.EditOrderDetailDto;
import com.example.springrestapi.models.OrderDetailDto;
import com.example.springrestapi.responseBodies.OrderDetailResponse;
import com.example.springrestapi.services.interfaces.OrderDetailService;

@RestController
@RequestMapping(RequestConfig.BASE_PROTECTED_URL + "/orders/{orderId}/details")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderService;

    @PostMapping
    public ResponseEntity<OrderDetailResponse> createOrderDetail(@Valid @RequestBody OrderDetailDto body,
            @PathVariable(value = "orderId") UUID orderId) throws Exception {

        OrderDetailResponse response = orderService.createOrderDetail(body, orderId);
        return new ResponseEntity<OrderDetailResponse>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<OrderDetailResponse> editOrderDetail(@Valid @RequestBody EditOrderDetailDto body,
            @PathVariable(value = "orderId") UUID orderId,
            @PathVariable(value = "id") UUID id) throws Exception {

        OrderDetailResponse response = orderService.editOrderDetail(body, orderId, id);
        return new ResponseEntity<OrderDetailResponse>(response, HttpStatus.OK);
    }
}
