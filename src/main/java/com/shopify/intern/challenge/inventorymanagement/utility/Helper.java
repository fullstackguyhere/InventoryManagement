package com.shopify.intern.challenge.inventorymanagement.utility;

import com.shopify.intern.challenge.inventorymanagement.model.InventoryItem;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

public class Helper {
    public static String[] getHeaders(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }


    public static ResponseEntity getResponse(String csvFileName, List<InventoryItem> items, HttpHeaders headers) {
        if(items.size() == 0) {
            headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(
                    Constants.NO_DATA_IN_RANGE_MESSAGE,
                    headers,
                    HttpStatus.OK
            );
        }
        InputStreamResource fileInputStream = WriteCsvToResponse.writeInventoryItemsToCSV(items);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + csvFileName);
        // defining the custom Content-Type
        headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");

        return new ResponseEntity<>(
                fileInputStream,
                headers,
                HttpStatus.OK
        );
    }
}
