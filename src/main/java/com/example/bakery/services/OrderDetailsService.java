package com.example.bakery.services;

import com.example.bakery.models.OrderDetails;
import com.example.bakery.repositories.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    public List<OrderDetails> getAllOrderDetails() {
        return orderDetailsRepository.findAll();
    }

    public Optional<OrderDetails> getOrderDetailsById(Integer id) {
        return orderDetailsRepository.findById(id);
    }

    public OrderDetails createOrderDetails(OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }

    public Optional<OrderDetails> updateOrderDetails(Integer id, OrderDetails orderDetails) {
        return orderDetailsRepository.findById(id)
                .map(existingOrderDetails -> {
                    existingOrderDetails.setOrder(orderDetails.getOrder());
                    existingOrderDetails.setProduct(orderDetails.getProduct());
                    existingOrderDetails.setUpdatedAt(orderDetails.getUpdatedAt());
                    return orderDetailsRepository.save(existingOrderDetails);
                });
    }

    public boolean deleteOrderDetails(Integer id) {
        return orderDetailsRepository.findById(id)
                .map(orderDetails -> {
                    orderDetailsRepository.delete(orderDetails);
                    return true;
                })
                .orElse(false);
    }
}
