package com.example.bakery.dto;

import java.util.List;

import com.example.bakery.models.OrderStatus;

public class OrderRequestDTO {
    private List<OrderDetailRequestDTO> orderDetails;
    private Long receiverId;  // Thêm trường này
    private OrderStatus status;  // Thêm trường này nếu cần

    public static class OrderDetailRequestDTO {
        private Long productId;
        private int quantity;
        private double price;

        // Getters và Setters
        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }

    // Getter và Setter cho orderDetails
    public List<OrderDetailRequestDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailRequestDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    // Thêm getter và setter cho receiverId
    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    // Thêm getter và setter cho status
    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
