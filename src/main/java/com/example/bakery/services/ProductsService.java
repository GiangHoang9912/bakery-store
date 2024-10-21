package com.example.bakery.services;

import com.example.bakery.models.Products;
import com.example.bakery.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.nio.file.StandardCopyOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductsService {

    private final ProductsRepository productsRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductsService.class);

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

    public Products createProduct(Products product, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            String imagePath = saveImage(imageFile);
            product.setImage(imagePath);
        }
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return productsRepository.save(product);
    }

    public Optional<Products> updateProduct(Long id, Products product, MultipartFile imageFile) throws IOException {
        return productsRepository.findById(id)
            .map(existingProduct -> {
                existingProduct.setName(product.getName());
                existingProduct.setPrice(product.getPrice());
                if (imageFile != null && !imageFile.isEmpty()) {
                    try {
                        String imagePath = saveImage(imageFile);
                        existingProduct.setImage(imagePath);
                    } catch (IOException e) {
                        throw new RuntimeException("Không thể lưu hình ảnh", e);
                    }
                }
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

    private static final String UPLOAD_DIR = "public";

    private String saveImage(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(UPLOAD_DIR).toAbsolutePath().normalize();
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        logger.info("Đã lưu ảnh tại: {}", filePath.toString());
        return "/public/" + fileName;
    }
}
