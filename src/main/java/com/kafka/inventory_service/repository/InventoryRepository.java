package com.kafka.inventory_service.repository;


import com.kafka.inventory_service.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long>{
    boolean existsBySku(String sku);
    Optional<Inventory> findBySku(String sku);
    Optional<Inventory> findByProductId(Long productId);
}