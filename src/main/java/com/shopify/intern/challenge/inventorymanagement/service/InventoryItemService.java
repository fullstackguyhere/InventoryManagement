package com.shopify.intern.challenge.inventorymanagement.service;

import com.shopify.intern.challenge.inventorymanagement.exception.ResourceNotFoundException;
import com.shopify.intern.challenge.inventorymanagement.model.DeliveryStatus;
import com.shopify.intern.challenge.inventorymanagement.model.InventoryItem;
import com.shopify.intern.challenge.inventorymanagement.repository.InventoryItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InventoryItemService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    /**
     * Add inventory Item
     * @param item
     */
    public void addInventoryItem(InventoryItem item) {
        log.info("Saving Inventory Item: {}", item);
        inventoryItemRepository.save(item);
    }

    /**
     * Delete Inventory Item
     * @param id
     * @throws ResourceNotFoundException
     */
    public void deleteInventoryItem(Long id) throws ResourceNotFoundException {
        InventoryItem item = inventoryItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory Item not found with id :: " + id));

        inventoryItemRepository.delete(item);
        log.info("Inventory Item deleted: {}", item.toString());
    }

    /**
     * Get All Inventory Items
     * @return
     */
    public List<InventoryItem> getAllInventoryItems() {
        log.info("Fetching All Inventory Items");
        return inventoryItemRepository.findAll();
    }

    /**
     * Update Inventory Item
     * @param id
     * @param updatedItemDetails
     * @return
     * @throws ResourceNotFoundException
     */
    public InventoryItem updateInventoryItem(Long id, InventoryItem updatedItemDetails) throws ResourceNotFoundException {
        InventoryItem item = inventoryItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory Item not found with id :: " + id));

        item.setPickUpDate(updatedItemDetails.getPickUpDate());
        item.setDescription(updatedItemDetails.getDescription());
        item.setItemCost(updatedItemDetails.getItemCost());
        item.setCustomDuty(updatedItemDetails.getCustomDuty());
        item.setSourceName(updatedItemDetails.getSourceName());
        item.setDestinationName(updatedItemDetails.getDestinationName());
        item.setEstimatedDeliveryDate(updatedItemDetails.getEstimatedDeliveryDate());
        item.setActualDeliveryDate(updatedItemDetails.getActualDeliveryDate());
        item.setStatus(updatedItemDetails.getStatus());
        inventoryItemRepository.save(item);
        log.info("Updating Inventory Item: {}", item);
        return item;
    }

}
