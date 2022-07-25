package com.example.springrestapi.responseBodies;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleOrderResponse {
    private UUID orderId;

    private UUID userId;

    List<UUID> orderDetailIds;

    Instant createdDate;

    private Instant modifiedDate;

    private String comment;

    private String status;
}
