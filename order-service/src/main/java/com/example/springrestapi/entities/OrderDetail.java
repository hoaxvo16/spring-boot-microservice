package com.example.springrestapi.entities;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 16)
    private UUID detailId;

    @Column(length = 8)
    private String itemId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;

    @CreatedDate
    Instant createdDate;

    private Instant modifiedDate;

    private int quantity;

    @Column(length = 64)
    private String status;

    public OrderDetail(Order order, int quantity, String status, String itemId) {
        this.order = order;
        this.quantity = quantity;
        this.status = status;
        this.itemId = itemId;
        this.createdDate = Instant.now();
        this.modifiedDate = Instant.now();
    }

}
