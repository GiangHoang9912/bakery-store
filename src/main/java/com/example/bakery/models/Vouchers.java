package com.example.bakery.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Vouchers")
public class Vouchers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private Float discount;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Products product;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private LocalDateTime expiredAt;

    // Constructors, getters, and setters
    // Default constructor
    public Vouchers() {
    }

    // Parameterized constructor
    public Vouchers(String code, Float discount, Products product, LocalDateTime expiredAt) {
        this.code = code;
        this.discount = discount;
        this.product = product;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.expiredAt = expiredAt;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(LocalDateTime expiredAt) {
        this.expiredAt = expiredAt;
    }

    @Override
    public String toString() {
        return "Vouchers{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", discount=" + discount +
                ", product=" + product +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", expiredAt=" + expiredAt +
                '}';
    }
}
