package com.example.springrestapi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.springrestapi.entities.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {
    @Query(value = "SELECT * FROM order_detail where detail_id =:detail_id and order_id=:order_id ;", nativeQuery = true)
    OrderDetail findByOrderDetailIdAndOrderId(@Param("detail_id") UUID orderDetailId, @Param("order_id") UUID orderId);
}
