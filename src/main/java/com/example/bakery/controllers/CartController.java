package com.example.bakery.controllers;

import com.example.bakery.dto.*;
import com.example.bakery.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartForUser(userId));
    }

    @PostMapping("/add")
    public ResponseEntity<CartDto> addToCart(@RequestBody AddToCartDto addToCartDto) {
        return ResponseEntity.ok(cartService.addToCart(addToCartDto));
    }

    @PutMapping("/update")
    public ResponseEntity<CartDto> updateCartItem(@RequestBody UpdateCartItemDto updateCartItemDto) {
        return ResponseEntity.ok(cartService.updateCartItem(updateCartItemDto));
    }

    @DeleteMapping("/{userId}/remove/{productId}")
    public ResponseEntity<CartDto> removeFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        return ResponseEntity.ok(cartService.removeFromCart(userId, productId));
    }
}