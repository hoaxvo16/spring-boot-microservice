package com.example.springrestapi.messages.data;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogItemQuantityMessage implements Serializable {
    private String itemId;
    private int quantity;
}
