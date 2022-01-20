package com.shopify.intern.challenge.inventorymanagement.controller;

import com.shopify.intern.challenge.inventorymanagement.exception.ResourceNotFoundException;
import com.shopify.intern.challenge.inventorymanagement.model.InventoryItem;
import com.shopify.intern.challenge.inventorymanagement.service.DataExportService;
import com.shopify.intern.challenge.inventorymanagement.utility.Constants;
import com.shopify.intern.challenge.inventorymanagement.utility.OperationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.shopify.intern.challenge.inventorymanagement.service.InventoryItemService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.shopify.intern.challenge.inventorymanagement.utility.Helper.getResponse;

@RestController
// versioning our api.
@RequestMapping("/api/v1")
public class InventoryController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private InventoryItemService inventoryItemService;

    @Autowired
    private DataExportService dataExportService;

    /**
     * Add Inventory Item.
     *
     * @param item the inventory item
     * @return nothing
     */
    @RequestMapping(method= RequestMethod.POST, value="/item")
    public void addInventoryItem(@RequestBody InventoryItem item) {
        inventoryItemService.addInventoryItem(item);
        log.info("Inventory Item added: {}", item.toString());
    }

    /**
     * Delete Inventory Item.
     *
     * @param id the inventory item id
     * @return the map
     * @throws Exception the exception
     */
    @DeleteMapping("/item/{id}")
    public Map<String, Boolean> deleteInventoryItem(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        inventoryItemService.deleteInventoryItem(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    /**
     * Update user response entity.
     *
     * @param id the inventory item id
     * @param itemDetails the inventory item details
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */
    @PutMapping("item/{id}")
    public ResponseEntity<InventoryItem> editInventoryItem(@PathVariable(value = "id") Long id, @RequestBody InventoryItem itemDetails) throws ResourceNotFoundException {
        InventoryItem updatedItem = inventoryItemService.updateInventoryItem(id, itemDetails);
        log.info("Inventory Item updated: {}", itemDetails.toString());
        return ResponseEntity.ok(updatedItem);
    }

    /**
     * Get all inventory items list.
     * Implemented via pagination
     * @param pageNo page number to access list of items
     * @param pageSize page size to display
     * @param sortBy sorting field to sort result
     * @return the list
     */
    @GetMapping("/item")
    public ResponseEntity<List<InventoryItem>> getAllEmployees(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<InventoryItem> list = dataExportService.getAllInventoryItems(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<InventoryItem>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Export All Inventory Items to CSV.
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */
    @RequestMapping(value = "/csv/all", produces = "text/csv")
    public  ResponseEntity csvAll(HttpServletResponse response) throws ResourceNotFoundException {

        HttpHeaders headers = new HttpHeaders();
        List<InventoryItem> items = inventoryItemService.getAllInventoryItems();
        log.info("Fetching CSV Files for All Inventory items: {}", items.toArray());
        return getResponse(Constants.INVENTORY_ALL_FILENAME, items, headers);
    }

    /**
     * Export All Inventory Items according to operationType to CSV.
     * @param operationType Type of Operation for export
     * @param firstValue first parameter required
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */
    @RequestMapping(value = "/csv/{operationType}/{firstValue}", produces = "text/csv")
    public  ResponseEntity csvSingleParams(@PathVariable String operationType, @PathVariable String firstValue,
                                           HttpServletResponse response) throws ResourceNotFoundException {

        HttpHeaders headers = new HttpHeaders();

        if(OperationType.SOURCE.toString().equals(operationType.toUpperCase())) {
            List<InventoryItem> items = dataExportService.getAllInventoryItemsWithSourceName(firstValue);
            log.info("Fetching CSV Files for Source Name Inventory items: {}", items.toArray());
            return getResponse(Constants.INVENTORY_WITH_SOURCE_FILENAME, items, headers);
        }
        if(OperationType.DESTINATION.toString().equals(operationType.toUpperCase())) {
            List<InventoryItem> items = dataExportService.getAllInventoryItemsWithDestinationName(firstValue);
            log.info("Fetching CSV Files for Destination Name Inventory items: {}", items.toArray());
            return getResponse(Constants.INVENTORY_WITH_DESTINATION_FILENAME, items, headers);
        }
        if(OperationType.DELIVERY_STATUS.toString().equals(operationType.toUpperCase())) {
            List<InventoryItem> items = dataExportService.getAllInventoryItemsWithDeliveryStatus(firstValue);
            log.info("Fetching CSV Files for Inventory items with Delivery Status: {}", items.toArray());
            return getResponse(Constants.INVENTORY_WITH_STATUS_FILENAME, items, headers);
        }

        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        return new ResponseEntity<>(
                Constants.NO_DATA_IN_RANGE_MESSAGE,
                headers,
                HttpStatus.OK
        );
    }

    /**
     * Export All Inventory Items according to operationType to CSV.
     * @param operationType Type of Operation for export
     * @param firstValue first parameter required
     * @param secondValue second parameter required
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */
    @RequestMapping(value = "/csv/{operationType}/{firstValue}/{secondValue}", produces = "text/csv")
    public  ResponseEntity csvTwoParams(@PathVariable String operationType, @PathVariable String firstValue,
                                                @PathVariable String secondValue, HttpServletResponse response)
                                                throws IOException, ResourceNotFoundException {
        HttpHeaders headers = new HttpHeaders();

        if(OperationType.PICK_UP_DATE.toString().equals(operationType.toUpperCase())) {
            List<InventoryItem> items = dataExportService.getAllInventoryItemsBetweenPickUpDate(firstValue, secondValue);
            log.info("Fetching CSV Files for Inventory items Between Pick Up Date: {}", items.toArray());
            return getResponse(Constants.INVENTORY_WITH_PICK_UP_DATE_FILENAME, items, headers);
        }

        if(OperationType.ESTIMATED_DELIVERY_DATE.toString().equals(operationType.toUpperCase())) {
            List<InventoryItem> items = dataExportService.getAllInventoryItemsBetweenEstimatedDate(firstValue, secondValue);
            log.info("Fetching CSV Files for Inventory items Between Estimated Date: {}", items.toArray());
            return getResponse(Constants.INVENTORY_WITH_ESTIMATED_DELIVERY_FILENAME, items, headers);
        }
        if(OperationType.SOURCE_DESTINATION.toString().equals(operationType.toUpperCase())) {
            List<InventoryItem> items = dataExportService.getAllInventoryItemsWithSourceNameAndDestinationName(firstValue, secondValue);
            log.info("Fetching CSV Files for Inventory items Between Source and Destination: {}", items.toArray());
            return getResponse(Constants.INVENTORY_WITH_SOURCE_AND_DESTINATION_FILENAME, items, headers);
        }

        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        return new ResponseEntity<>(
                Constants.NO_DATA_IN_RANGE_MESSAGE,
                headers,
                HttpStatus.OK
        );
    }

}
