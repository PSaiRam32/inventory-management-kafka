package com.kafka.inventory_service.service;

import com.kafka.inventory_service.Messaging.event.BaseEvent;
import com.kafka.inventory_service.Messaging.event.ProductPayload;
import com.kafka.inventory_service.entity.Inventory;
import com.kafka.inventory_service.entity.InventoryStatus;
import com.kafka.inventory_service.entity.ProcessedEvent;
import com.kafka.inventory_service.repository.InventoryRepository;
import com.kafka.inventory_service.repository.ProcessedEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;
    private final ProcessedEventRepository processedEventRepository;

//    @Override
//    @Transactional
//    public void createInventory(BaseEvent<ProductPayload> event){
//        if(processedEventRepository.existsByEventId(event.getEventId())){
//            log.warn("Duplicate Event {} ignored.", event.getEventId());
//            return;
//        }
//        ProductPayload payload = event.getPayload();
//        Inventory inventory = Inventory.builder()
//                .productId(payload.getId())
//                .sku(payload.getSku())
//                .productName(payload.getProductName())
//                .availableQuantity(100)
//                .reservedQuantity(0)
//                .warehouse("HYD-01")
//                .status(InventoryStatus.ACTIVE)
//                .build();
//        inventoryRepository.save(inventory);
//        processedEventRepository.save(
//                ProcessedEvent.builder()
//                        .eventId(event.getEventId())
//                        .processedAt(Instant.now())
//                        .build());
//        log.info("""
//                ==========================================
//                Inventory Created Successfully
//
//                ProductId : {}
//                SKU       : {}
//                Warehouse : {}
//
//                ==========================================
//                """,
//                payload.getId(),
//                payload.getSku(),
//                inventory.getWarehouse());
//    }

    @Override
    @Transactional
    public void processInventory(BaseEvent<ProductPayload> event){
        if (processedEventRepository.existsByEventId(event.getEventId())){
            log.warn("Duplicate Event {} ignored.", event.getEventId());
            return;
        }
        switch (event.getEventType()){
            case PRODUCT_CREATED -> createInventory(event);
            case PRODUCT_UPDATED -> updateInventory(event);
            case PRODUCT_DELETED -> deleteInventory(event);
        }
        processedEventRepository.save(ProcessedEvent.builder()
                        .eventId(event.getEventId())
                        .processedAt(Instant.now())
                        .build());
    }
    private void createInventory(BaseEvent<ProductPayload> event){
        ProductPayload payload = event.getPayload();
        Inventory inventory = Inventory.builder()
                .productId(payload.getId())
                .sku(payload.getSku())
                .productName(payload.getProductName())
                .availableQuantity(100)
                .reservedQuantity(0)
                .warehouse("HYD-01")
                .status(InventoryStatus.ACTIVE)
                .build();
        inventoryRepository.save(inventory);
        log.info("""
                ==========================================
                Inventory Created Successfully
                ProductId : {}
                SKU       : {}
                ==========================================
                """,
                payload.getId(),payload.getSku());
    }

    private void updateInventory(BaseEvent<ProductPayload> event){
        ProductPayload payload = event.getPayload();
        Inventory inventory = inventoryRepository.findBySku(payload.getSku())
                .orElseThrow(() -> new RuntimeException("Inventory not found for SKU=" + payload.getSku()));
        inventory.setProductName(payload.getProductName());
        inventoryRepository.save(inventory);
        log.info("""
                ==========================================
                Inventory Updated Successfully
                ProductId : {}
                SKU       : {}
                ==========================================
                """,
                payload.getId(),payload.getSku());
    }
    private void deleteInventory(BaseEvent<ProductPayload> event){
        ProductPayload payload = event.getPayload();
        Inventory inventory = inventoryRepository.findBySku(payload.getSku()).orElseThrow(() ->
                        new RuntimeException("Inventory not found for SKU=" + payload.getSku()));
        inventory.setStatus(InventoryStatus.DISCONTINUED);
        inventoryRepository.save(inventory);
        log.info("""
                ==========================================
                Inventory Marked INACTIVE
                ProductId : {}
                SKU       : {}
                ==========================================
                """,
                payload.getId(),
                payload.getSku());
    }
}