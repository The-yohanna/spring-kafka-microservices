package com.johanna.orderservice.controller;

import com.johanna.basedomains.dto.Order;
import com.johanna.basedomains.dto.OrderEvent;
import com.johanna.orderservice.kafka.OrderProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1")
public class OrderController {

    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("/orders")
    public ResponseEntity<String> placeOrder(@RequestBody Order order){

        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("New order to be processed");
        orderEvent.setOrder(order);

        orderProducer.sendMessage(orderEvent);

        return ResponseEntity.ok("Order placed successfully...");

    }

}
