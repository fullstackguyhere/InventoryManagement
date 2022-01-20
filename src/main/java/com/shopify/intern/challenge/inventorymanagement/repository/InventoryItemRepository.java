package com.shopify.intern.challenge.inventorymanagement.repository;

import com.shopify.intern.challenge.inventorymanagement.model.DeliveryStatus;
import com.shopify.intern.challenge.inventorymanagement.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    public List<InventoryItem> findByPickUpDateIsBetween(Date fromDate, Date toDate);
    public List<InventoryItem> findByEstimatedDeliveryDateBetween(Date fromDate, Date toDate);
    public List<InventoryItem> findBySourceName(String sourceName);
    public List<InventoryItem> findByDestinationName(String destinationName);
    public List<InventoryItem> findBySourceNameAndDestinationName(String sourceName, String destinationName);
    public List<InventoryItem> findByStatus(DeliveryStatus deliveryStatus);
}
