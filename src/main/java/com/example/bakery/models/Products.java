package com.example.bakery.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private float price;
    private String image;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Vouchers> vouchers = new ArrayList<>();

    @JsonIgnore
    @OneToOne(mappedBy = "product")
    private OrderDetails orderDetail;

    // Controller and Getters and setters
    // Constructor
    public Products() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Products(String name, float price) {
        this.name = name;
        this.price = price;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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

    public List<Vouchers> getVouchers() {
        return vouchers;
    }

    public void setVouchers(List<Vouchers> vouchers) {
        this.vouchers = vouchers;
    }

    public OrderDetails getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetails orderDetail) {
        this.orderDetail = orderDetail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // Thêm phương thức để lấy URL đầy đủ của hình ảnh
    public String getImageUrl() {
        return image != null ? "http://localhost:8080" + image : null;
    }
}
