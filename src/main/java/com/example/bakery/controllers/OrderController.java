package com.example.bakery.controllers;

import com.example.bakery.dto.OrderDto;
import com.example.bakery.services.OrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        OrderDto order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/customer/{customerName}")
    public ResponseEntity<List<OrderDto>> getOrdersByCustomerName(@PathVariable String customerName) {
        List<OrderDto> orders = orderService.getOrdersByCustomerName(customerName);
        return ResponseEntity.ok(orders);
    }
}