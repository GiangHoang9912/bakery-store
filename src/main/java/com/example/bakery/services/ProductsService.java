package com.example.bakery.services;

import com.example.bakery.models.Products;
import com.example.bakery.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    public Optional<Products> getProductById(Long id) {
        return productsRepository.findById(id);
    }

    public Products createProduct(Products product) {
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return productsRepository.save(product);
    }

    public Optional<Products> updateProduct(Long id, Products product) {
        return productsRepository.findById(id)
            .map(existingProduct -> {
                existingProduct.setName(product.getName());
                existingProduct.setPrice(product.getPrice());
                existingProduct.setUpdatedAt(LocalDateTime.now());
                return productsRepository.save(existingProduct);
            });
    }

    public boolean deleteProduct(Long id) {
        return productsRepository.findById(id)
            .map(product -> {
                productsRepository.delete(product);
                return true;
            })
            .orElse(false);
    }
}
