package com.shopify.intern.challenge.inventorymanagement.utility;

import com.shopify.intern.challenge.inventorymanagement.model.InventoryItem;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import static com.shopify.intern.challenge.inventorymanagement.utility.Helper.getHeaders;

public class WriteCsvToResponse {

    private static final Logger LOGGER = LoggerFactory.getLogger(WriteCsvToResponse.class);

    public static InputStreamResource writeInventoryItemsToCSV(List<InventoryItem> items) {

        String[] csvHeader = getHeaders(Headers.class);

        ByteArrayInputStream byteArrayOutputStream;
        try (
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                // defining the CSV printer
                CSVPrinter csvPrinter = new CSVPrinter(
                        new PrintWriter(out),
                        // withHeader is optional
                        CSVFormat.DEFAULT.withHeader(csvHeader)
                );
        ) {
            // populating the CSV content
            for (InventoryItem item : items)
                csvPrinter.printRecord(Arrays.asList(item.toString().split("\\s*,\\s*")));

            // writing the underlying stream
            csvPrinter.flush();

            byteArrayOutputStream = new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return new InputStreamResource(byteArrayOutputStream);
    }
}