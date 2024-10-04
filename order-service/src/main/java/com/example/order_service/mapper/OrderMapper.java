package com.example.order_service.mapper;

import com.example.kafkaevents.OrderEvent;
import com.example.order_service.entity.Order;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class OrderMapper {

    public OrderEvent mapToOrderEvent(Order order) {
        if (order == null) {
            return null;
        }
        OrderEvent orderEvent = new OrderEvent();

        orderEvent.setProduct(order.getProduct());
        orderEvent.setQuantity(order.getQuantity());
        return orderEvent;
    }
}
