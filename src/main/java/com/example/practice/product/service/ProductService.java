package com.example.practice.product.service;

import com.example.practice.product.dto.ProductRequest;
import com.example.practice.product.dto.ProductResponse;
import com.example.practice.product.entity.Product;
import com.example.practice.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest request) {

        Product product = new Product(request.getName(), request.getPrice());
        Product saved = productRepository.save(product);
        return new ProductResponse(saved);
    }

    public ProductResponse getProduct(Long id) {

        Product product = findProductById(id);
        return new ProductResponse(product);
    }

    public List<ProductResponse> getProducts() {

        return productRepository.findAll().stream().map(ProductResponse::new).toList();
    }

    @Transactional
    public ProductResponse updateProduct(Long id, ProductRequest request) {

        Product product = findProductById(id);
        product.update(request.getName(), request.getPrice());
        return new ProductResponse(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = findProductById(id);
        productRepository.delete(product);
    }

    private Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다. id=" + id));
    }
}
