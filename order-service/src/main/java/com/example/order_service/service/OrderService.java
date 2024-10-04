package com.example.order_service.service;

import com.example.kafkaevents.Event;
import com.example.kafkaevents.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Value("${app.kafka.topic-order}")
    private String topicName;

    private final KafkaTemplate<String, Event> kafkaTemplate;

    public void sendOrderEvent(OrderEvent event) {
        kafkaTemplate.send(topicName, event); // отправляем в топик order-topic
    }
}
