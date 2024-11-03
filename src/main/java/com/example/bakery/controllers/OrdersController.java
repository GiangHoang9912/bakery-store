package com.example.bakery.controllers;

import com.example.bakery.dto.OrderRequestDTO;
import com.example.bakery.models.OrderStatus;
import com.example.bakery.models.Orders;
import com.example.bakery.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.bakery.security.UserPrincipal;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping
    public List<Orders> getAllOrders() {
        return ordersService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
        return ordersService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Orders>> getOrdersByUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long userId = userPrincipal.getId();
        List<Orders> orders = ordersService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Orders> createOrder(@RequestBody OrderRequestDTO orderRequest) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            Long userId = userPrincipal.getId();
            Orders createdOrder = ordersService.createOrder(orderRequest, userId);
            return ResponseEntity.ok(createdOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Long id, @RequestBody Orders orderDetails) {
        return ordersService.updateOrder(id, orderDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        return ordersService.deleteOrder(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Orders> updateOrderStatus(@PathVariable Long id, @RequestBody OrderStatus newStatus) {
        return ordersService.updateOrderStatus(id, newStatus)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
