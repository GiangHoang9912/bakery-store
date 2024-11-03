package com.example.bakery.services;

import com.example.bakery.dto.RegisterRequest;
import com.example.bakery.models.Orders;
import com.example.bakery.models.User;
import com.example.bakery.repositories.UserRepository;
import com.example.bakery.repositories.OrdersRepository;
import com.example.bakery.repositories.OrderDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersService {
    private static final Logger logger = LoggerFactory.getLogger(UsersService.class);
    private final UserRepository userRepository;
    private final OrdersRepository ordersRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UserRepository userRepository, PasswordEncoder passwordEncoder, OrdersRepository ordersRepository, OrderDetailsRepository orderDetailsRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.ordersRepository = ordersRepository;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    public User registerUser(RegisterRequest registerRequest) {
        try {
            User newUser = new User();
            newUser.setFullname(registerRequest.getFullname());
            newUser.setEmail(registerRequest.getEmail());
            newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            newUser.setPhone(registerRequest.getPhone());
            newUser.setRole(1);

            return userRepository.save(newUser);
        } catch (Exception e) {
            logger.error("Error registering user", e);
            throw new RuntimeException("Error registering user", e);
        }
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setFullname(userDetails.getFullname());
            user.setPhone(userDetails.getPhone());
            return userRepository.save(user);
        }
        return null;
    }

    @Transactional
    public boolean deleteUser(Long id) {
        try {
            User user = userRepository.findById(id).orElse(null);
            if (user != null) {
                if (user.getRole() == 0) {
                    return false;
                }
                List<Orders> orders = ordersRepository.findByUserId(id);
                for (Orders order : orders) {
                    orderDetailsRepository.deleteByOrderId(order.getId());
                }
                ordersRepository.deleteByUserId(id);
                userRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("Không thể xóa user có id=" + id + ". User có đơn hàng liên kết.", e);
            return false;
        }
    }

    public User resetPassword(Long id, String newPassword) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            return userRepository.save(user);
        }
        return null;
    }
}
