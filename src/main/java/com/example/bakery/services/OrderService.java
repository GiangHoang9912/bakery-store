package com.example.bakery.services;

import com.example.bakery.dto.OrderDto;
import com.example.bakery.dto.OrderItemDto;
import com.example.bakery.models.Order;
import com.example.bakery.models.OrderItem;
import com.example.bakery.repository.OrderRepository;
import com.example.bakery.exceptions.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
        return convertToDto(order);
    }

    public List<OrderDto> getOrdersByCustomerName(String customerName) {
        List<Order> orders = orderRepository.findByCustomerNameContainingIgnoreCase(customerName);
        return orders.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private OrderDto convertToDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setCustomerName(order.getCustomerName());
        dto.setOrderDate(order.getOrderDate());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus());
        dto.setItems(order.getItems().stream().map(this::convertToItemDto).collect(Collectors.toList()));
        return dto;
    }

    private OrderItemDto convertToItemDto(OrderItem item) {
        OrderItemDto dto = new OrderItemDto();
        dto.setProductId(item.getProduct().getId());
        dto.setProductName(item.getProduct().getName());
        dto.setQuantity(item.getQuantity());
        dto.setPrice(item.getPrice());
        return dto;
    }
}

    