package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.entity.Order;
import com.ecommerce.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
// Order creation API
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {

        Order savedOrder = orderService.createOrder(order);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedOrder);
    }
}