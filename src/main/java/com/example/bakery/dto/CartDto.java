package com.example.bakery.dto;

import java.math.BigDecimal;
import java.util.List;

public class CartDto {
    private Long id;
    private List<CartItemDto> items;
    private BigDecimal totalAmount;

    // Getters and setters
}