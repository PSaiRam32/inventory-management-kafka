package com.kafka.inventory_service.Messaging.consumer;

import com.kafka.inventory_service.Messaging.event.BaseEvent;
import com.kafka.inventory_service.Messaging.event.ProductEventValidator;
import com.kafka.inventory_service.Messaging.event.ProductPayload;
import com.kafka.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@KafkaListener(topics = "product-events",containerFactory = "kafkaListenerContainerFactory")
public class ProductEventConsumer {

    private final InventoryService inventoryService;
    private final ProductEventValidator validator;

    @KafkaHandler
    public void handle(BaseEvent<ProductPayload> event,Acknowledgment acknowledgment){
        log.info("""
                 =====================================
                Product Event Received

                EventId : {}
                Type    : {}

                =====================================
                """,
                event.getEventId(),
                event.getEventType());
        validator.validate(event);
        inventoryService.processInventory(event);
        acknowledgment.acknowledge();
        log.info("Event {} processed successfully",event.getEventId());
        log.info("Offset committed for EventId={}",event.getEventId());
    }
}