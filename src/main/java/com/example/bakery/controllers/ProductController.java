package com.example.bakery.controllers;

import com.example.bakery.dto.ProductDto;
import com.example.bakery.dto.ProductUpdateDto;
import com.example.bakery.models.Product;
import com.example.bakery.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ... existing methods ...

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateDto productUpdateDto) {
        Product updatedProduct = productService.updateProduct(id, productUpdateDto);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
}