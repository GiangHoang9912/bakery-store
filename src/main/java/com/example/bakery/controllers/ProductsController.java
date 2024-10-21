package com.example.bakery.controllers;

import com.example.bakery.models.Products;
import com.example.bakery.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    private final ProductsService productsService;
    private static final Logger logger = LoggerFactory.getLogger(ProductsController.class);

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts() {
        List<Products> products = productsService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable Long id) {
        return productsService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createProduct(
            @RequestParam("name") String name,
            @RequestParam("price") float price,
            @RequestPart(value = "image", required = false) MultipartFile imageFile) {
        try {
            Products productToCreate = new Products();
            productToCreate.setName(name);
            productToCreate.setPrice(price);

            if (imageFile != null) {
                logger.info("Kích thước tệp ảnh: {} bytes", imageFile.getSize());
            }
            Products createdProduct = productsService.createProduct(productToCreate, imageFile);
            logger.info("Đường dẫn ảnh đã lưu: {}", createdProduct.getImage());
            return ResponseEntity.ok(createdProduct);
        } catch (IOException e) {
            logger.error("Lỗi khi xử lý tệp", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi xử lý tệp: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProduct(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("price") float price,
            @RequestPart(value = "image", required = false) MultipartFile imageFile) {
        try {
            Products productToUpdate = new Products();
            productToUpdate.setName(name);
            productToUpdate.setPrice(price);
            return productsService.updateProduct(id, productToUpdate, imageFile)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        boolean deleted = productsService.deleteProduct(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
