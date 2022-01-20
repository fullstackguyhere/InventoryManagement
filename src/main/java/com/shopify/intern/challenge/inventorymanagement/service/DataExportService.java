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
public class DataExportService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    /**
     * Get All Inventory Items Between PickUp Dates.
     *
     * @param fromDate From Date
     * @param toDate To Date
     * @return nothing
     */
    public List<InventoryItem> getAllInventoryItemsBetweenPickUpDate(String fromDate, String toDate) throws ResourceNotFoundException {
        try{
            Date fromDateFormated = new SimpleDateFormat("yyyy-mm-dd").parse(fromDate);
            Date toDateFormatted = new SimpleDateFormat("yyyy-mm-dd").parse(toDate);
            log.info("Fetching Inventory Items with Pick Up Date Between: {}, {}", fromDate, toDate);
            return inventoryItemRepository.findByPickUpDateIsBetween(fromDateFormated, toDateFormatted);
        } catch (ParseException ex) {
            throw new ResourceNotFoundException(ex.getMessage());
        }
    }

    /**
     * Get All Inventory Items Between Estimated Dates.
     *
     * @param fromDate From Date
     * @param toDate To Date
     * @return nothing
     */
    public List<InventoryItem> getAllInventoryItemsBetweenEstimatedDate(String fromDate, String toDate) throws ResourceNotFoundException {
        try{
            Date fromDateFormated = new SimpleDateFormat("yyyy-mm-dd").parse(fromDate);
            Date toDateFormatted = new SimpleDateFormat("yyyy-mm-dd").parse(toDate);
            log.info("Fetching Inventory Items with Estimated Date Between: {}, {}", fromDate, toDate);
            return inventoryItemRepository.findByEstimatedDeliveryDateBetween(fromDateFormated, toDateFormatted);
        } catch (ParseException ex) {
            throw new ResourceNotFoundException(ex.getMessage());
        }
    }

    /**
     * Get All Inventory Items With Source Name
     * @param sourceName
     * @return
     * @throws ResourceNotFoundException
     */
    public List<InventoryItem> getAllInventoryItemsWithSourceName(String sourceName) throws ResourceNotFoundException {
        try{
            log.info("Fetching Inventory Items with Source Name: {}", sourceName);
            return inventoryItemRepository.findBySourceName(sourceName);
        } catch (Exception ex) {
            throw new ResourceNotFoundException(ex.getMessage());
        }
    }

    /**
     * Get All Inventory Items With DestinationName
     * @param destinationName
     * @return
     * @throws ResourceNotFoundException
     */
    public List<InventoryItem> getAllInventoryItemsWithDestinationName(String destinationName) throws ResourceNotFoundException {
        try{
            log.info("Fetching Inventory Items with Destination Name: {}", destinationName);
            return inventoryItemRepository.findByDestinationName(destinationName);
        } catch (Exception ex) {
            throw new ResourceNotFoundException(ex.getMessage());
        }
    }

    /**
     * Get All Inventory Items With Source Name And DestinationName
     * @param sourceName
     * @param destinationName
     * @return
     * @throws ResourceNotFoundException
     */
    public List<InventoryItem> getAllInventoryItemsWithSourceNameAndDestinationName(String sourceName, String destinationName) throws ResourceNotFoundException {
        try{
            log.info("Fetching Inventory Items with Source And Destination name: {}, {}", sourceName, destinationName);
            return inventoryItemRepository.findBySourceNameAndDestinationName(sourceName, destinationName);
        } catch (Exception ex) {
            throw new ResourceNotFoundException(ex.getMessage());
        }
    }

    /**
     * Get All Inventory Items With DeliveryStatus
     * @param status
     * @return
     * @throws ResourceNotFoundException
     */
    public List<InventoryItem> getAllInventoryItemsWithDeliveryStatus(String status) throws ResourceNotFoundException {
        try{
            log.info("Fetching Inventory Items with Delivery Status: {}", status);
            return inventoryItemRepository.findByStatus(DeliveryStatus.valueOf(status));
        } catch (Exception ex) {
            throw new ResourceNotFoundException(ex.getMessage());
        }
    }

    /**
     * Get All Inventory Items
     * @param pageNo
     * @param pageSize
     * @param sortBy
     * @return
     */
    public List<InventoryItem> getAllInventoryItems(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<InventoryItem> pagedResult = inventoryItemRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            log.info("Fetched All Inventory Item: {}, {}, {}, {}", pagedResult.getContent(), pageNo, pageSize, sortBy);
            return pagedResult.getContent();
        } else {
            log.info("Fetched All Inventory Item: {}, {}, {}, {}", "empty", pageNo, pageSize, sortBy);
            return new ArrayList<InventoryItem>();
        }
    }

}
