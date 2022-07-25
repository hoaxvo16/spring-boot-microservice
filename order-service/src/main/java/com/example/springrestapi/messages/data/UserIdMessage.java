package com.example.springrestapi.messages.data;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserIdMessage implements Serializable {
    private UUID accountId;
}
