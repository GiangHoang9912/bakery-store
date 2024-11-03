package com.example.bakery.services;

import com.example.bakery.dto.OrderRequestDTO;
import com.example.bakery.models.OrderStatus;
import com.example.bakery.models.Orders;
import com.example.bakery.models.OrderDetails;
import com.example.bakery.models.Products;
import com.example.bakery.models.Receivers;
import com.example.bakery.models.User;

import com.example.bakery.repositories.OrdersRepository;
import com.example.bakery.repositories.ProductsRepository;
import com.example.bakery.repositories.ReceiverRepository;
import com.example.bakery.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ReceiverRepository receiversRepository;

    @Transactional
    public Orders createOrder(OrderRequestDTO orderRequest, Long userId) {
        Orders order = new Orders();
        order.setStatus(OrderStatus.PENDING);

        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user với ID: " + userId));
        order.setUser(user);

        // Thêm receiver vào order
        if (orderRequest.getReceiverId() != null) {
            Receivers receiver = receiversRepository.findById(orderRequest.getReceiverId())
                    .orElseThrow(() -> new RuntimeException(
                            "Không tìm thấy receiver với ID: " + orderRequest.getReceiverId()));
            order.setReceiver(receiver);
        }

        // Tạo các chi tiết đơn hàng
        for (OrderRequestDTO.OrderDetailRequestDTO detailDTO : orderRequest.getOrderDetails()) {
            Products product = productsRepository.findById(detailDTO.getProductId())
                    .orElseThrow(
                            () -> new RuntimeException("Không tìm thấy sản phẩm với ID: " + detailDTO.getProductId()));

            OrderDetails orderDetail = new OrderDetails();
            orderDetail.setProduct(product);
            orderDetail.setQuantity(detailDTO.getQuantity());
            orderDetail.setPrice(detailDTO.getPrice());

            order.addOrderDetail(orderDetail);
        }

        return ordersRepository.save(order);
    }

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

    public Optional<Orders> updateOrderStatus(Long id, OrderStatus newStatus) {
        return ordersRepository.findById(id)
                .map(existingOrder -> {
                    existingOrder.setStatus(newStatus);
                    existingOrder.setUpdatedAt(LocalDateTime.now());
                    return ordersRepository.save(existingOrder);
                });
    }

    public List<Orders> getOrdersByUserId(Long userId) {
        return ordersRepository.findByUserId(userId);
    }
}
