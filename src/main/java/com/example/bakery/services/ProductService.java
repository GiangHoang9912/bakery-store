package com.example.bakery.services;

import com.example.bakery.dto.ProductDto;
import com.example.bakery.dto.ProductUpdateDto;
import com.example.bakery.models.Product;
import com.example.bakery.repository.ProductRepository;
import com.example.bakery.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // ... existing methods ...

    public Product updateProduct(Long id, ProductUpdateDto productUpdateDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        if (productUpdateDto.getName() != null && !productUpdateDto.getName().trim().isEmpty()) {
            product.setName(productUpdateDto.getName());
        }

        if (productUpdateDto.getPrice() != null && productUpdateDto.getPrice().compareTo(BigDecimal.ZERO) > 0) {
            product.setPrice(productUpdateDto.getPrice());
        }

        if (productUpdateDto.getDescription() != null) {
            product.setDescription(productUpdateDto.getDescription());
        }

        return productRepository.save(product);
    }
}