package com.example.bakery.dto;

import java.math.BigDecimal;

public class ProductDto {
    private String name;
    private BigDecimal price;
    private String description;

    // Constructors
    public ProductDto() {}

    public ProductDto(String name, BigDecimal price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}