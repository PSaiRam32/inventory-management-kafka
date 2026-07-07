package com.kafka.inventory_service.service;

public interface ProcessedEventService{
    boolean isProcessed(String eventId);
    void markProcessed(String eventId);
}