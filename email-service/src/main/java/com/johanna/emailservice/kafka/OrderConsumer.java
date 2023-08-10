package com.johanna.emailservice.kafka;

import com.johanna.basedomains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumer(OrderEvent event){
        LOGGER.info(String.format("Email be sent for this order -> %s", event.toString()));
        // TODO send an email to customer
    }

}
