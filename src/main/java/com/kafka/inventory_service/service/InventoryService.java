package com.kafka.inventory_service.service;

import com.kafka.inventory_service.Messaging.event.BaseEvent;
import com.kafka.inventory_service.Messaging.event.ProductPayload;

public interface InventoryService{
//    void createInventory(BaseEvent<ProductPayload> event);
      void processInventory(BaseEvent<ProductPayload> event);
}
