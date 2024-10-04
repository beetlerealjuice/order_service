package com.example.order_service_status.listener;

import com.example.kafkaevents.Event;
import com.example.kafkaevents.OrderEvent;
import com.example.kafkaevents.OrderStatusEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderStatusListener {

    @Value("${app.kafka.topic-order-status}")
    private String topicName;

    private final KafkaTemplate<String, Event> kafkaTemplate;

    @KafkaListener(topics = "${app.kafka.topic-order}",
            groupId = "${app.kafka.group-id}",
            containerFactory = "kafkaMessageConcurrentKafkaListenerContainerFactory")
    public void listen(@Payload OrderEvent message,
                       @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp) {

            log.info("Received message: {}", message);
            log.info("Key: {}; Partition: {}; Topic: {}; Timestamp: {}", key, partition, topic, timestamp);

            OrderStatusEvent statusEvent = new OrderStatusEvent("CREATED", Instant.now());
            kafkaTemplate.send(topicName, statusEvent);
    }
}
