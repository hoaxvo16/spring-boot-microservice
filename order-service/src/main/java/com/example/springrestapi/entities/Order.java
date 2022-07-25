package com.example.springrestapi.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 16, name = "order_id")
    private UUID orderId;

    @Column(length = 16)
    private UUID userId;

    @OneToMany(targetEntity = Order.class, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH })
    @JoinColumn(name = "order_id")
    List<OrderDetail> orderDetails;

    @CreatedDate
    Instant createdDate;

    private Instant modifiedDate;

    @Column(length = 1000)
    private String comment;

    @Column(length = 64)
    private String status;

    public Order(String comment, String status, UUID userId) {
        this.comment = comment;
        this.status = status;
        this.createdDate = Instant.now();
        this.modifiedDate = Instant.now();
        this.orderDetails = new ArrayList<>();
        this.userId = userId;

    }

}