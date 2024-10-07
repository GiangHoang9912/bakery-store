package com.example.bakery.services;

import com.example.bakery.models.Orders;
import com.example.bakery.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    public Optional<Orders> getOrderById(Long id) {
        return ordersRepository.findById(id);
    }

    public Orders saveOrder(Orders order) {
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        return ordersRepository.save(order);
    }

    public Optional<Orders> updateOrder(Long id, Orders orderDetails) {
        return ordersRepository.findById(id)
            .map(existingOrder -> {
                existingOrder.setUser(orderDetails.getUser());
                existingOrder.setOrderDetails(orderDetails.getOrderDetails());
                existingOrder.setUpdatedAt(LocalDateTime.now());
                return ordersRepository.save(existingOrder);
            });
    }

    public boolean deleteOrder(Long id) {
        return ordersRepository.findById(id)
            .map(order -> {
                ordersRepository.delete(order);
                return true;
            })
            .orElse(false);
    }
}
