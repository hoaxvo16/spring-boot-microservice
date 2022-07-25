package com.example.springrestapi.models;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private UUID accountId;

    @NotBlank
    @Size(max = 64)
    private String status;

    @Size(max = 1000)
    private String comment;
}
