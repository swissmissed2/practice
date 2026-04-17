package com.example.practice.order.service;

import com.example.practice.order.dto.OrderRequest;
import com.example.practice.order.dto.OrderResponse;
import com.example.practice.order.entity.Order;
import com.example.practice.order.repository.OrderRepository;
import com.example.practice.product.entity.Product;
import com.example.practice.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderResponse createOrder(OrderRequest request) {

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "해당 상품이 존재하지 않습니다. id=" + request.getProductId()));

        Order order = new Order(product);
        Order saved = orderRepository.save(order);

        return new OrderResponse(saved);
    }

    @Transactional(readOnly = true)
    public OrderResponse getOrder(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "해당 주문이 존재하지 않습니다. id=" + id));

        return new OrderResponse(order);
    }
}
