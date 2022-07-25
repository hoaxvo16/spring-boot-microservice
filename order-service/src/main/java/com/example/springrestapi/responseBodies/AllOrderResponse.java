package com.example.springrestapi.responseBodies;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllOrderResponse {
    private UUID orderId;

    Instant createdDate;

    private Instant modifiedDate;

    private String comment;

    private String status;
}
