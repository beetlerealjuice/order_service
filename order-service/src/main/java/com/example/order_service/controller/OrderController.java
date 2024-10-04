package com.example.order_service.controller;

import com.example.order_service.entity.Order;
import com.example.order_service.mapper.OrderMapper;
import com.example.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody Order order) {
        orderService.sendOrderEvent(orderMapper.mapToOrderEvent(order));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
