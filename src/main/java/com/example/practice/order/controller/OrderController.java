package com.example.practice.order.controller;


import com.example.practice.order.dto.OrderRequest;
import com.example.practice.order.dto.OrderResponse;
import com.example.practice.order.repository.OrderRepository;
import com.example.practice.order.service.OrderService;
import com.example.practice.product.entity.Product;
import com.example.practice.product.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @Transactional
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest request) {

        OrderResponse response = orderService.createOrder(request);

        return ResponseEntity.created(URI.create("/api/orders/" + response.getOrderId())).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {

        OrderResponse response = orderService.getOrder(id);

        return ResponseEntity.ok(response);
    }
}
