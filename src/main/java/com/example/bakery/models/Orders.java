package com.example.bakery.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "order")
    private List<OrderDetails> orderDetails;

    // Constructors, getters, and setters
    // Default constructor
    public Orders() {
    }

    // Constructor with user
    public Orders(User user) {
        this.user = user;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Float getTotalPrice() {
        return orderDetails.stream()
                .map(detail -> detail.getProduct().getPrice() * 1)
                .reduce(0f, Float::sum);
    }

    public Float getTotalDiscount() {
        return orderDetails.stream()
                .map(detail -> (Float) (detail.getProduct().getVouchers().stream().map(voucher -> voucher.getDiscount())
                        
                        .reduce(0f, Float::sum)))
                .reduce(0f, Float::sum);
    }

    public Float getTotalPriceAfterDiscount() {
        return getTotalPrice() - getTotalDiscount();
    }
}
