package com.example.practice.order.dto;

import com.example.practice.order.entity.Order;
import lombok.Getter;

@Getter

public class OrderResponse {

    private Long orderId;

    private String productName;

    private Integer productPrice;

    public OrderResponse(Order order) {
        this.orderId = order.getId();
        this.productName = order.getProduct().getName();
        this.productPrice = order.getProduct().getPrice();
    }
}
