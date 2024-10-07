package com.example.bakery.controllers;

import com.example.bakery.models.OrderDetails;
import com.example.bakery.services.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;

    @Autowired
    public OrderDetailsController(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDetails>> getAllOrderDetails() {
        List<OrderDetails> orderDetails = orderDetailsService.getAllOrderDetails();
        return ResponseEntity.ok(orderDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetails> getOrderDetailsById(@PathVariable Integer id) {
        return orderDetailsService.getOrderDetailsById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrderDetails> createOrderDetails(@RequestBody OrderDetails orderDetails) {
        OrderDetails createdOrderDetails = orderDetailsService.createOrderDetails(orderDetails);
        return ResponseEntity.ok(createdOrderDetails);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetails> updateOrderDetails(@PathVariable Integer id, @RequestBody OrderDetails orderDetails) {
        return orderDetailsService.updateOrderDetails(id, orderDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetails(@PathVariable Integer id) {
        boolean deleted = orderDetailsService.deleteOrderDetails(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
