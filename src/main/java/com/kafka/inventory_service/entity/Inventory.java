package com.kafka.inventory_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private Long productId;
    @Column(nullable = false, unique = true)
    private String sku;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private Integer availableQuantity;
    @Column(nullable = false)
    private Integer reservedQuantity;
    @Column(nullable = false)
    private String warehouse;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InventoryStatus status;
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;
}