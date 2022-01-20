package com.shopify.intern.challenge.inventorymanagement;

import com.shopify.intern.challenge.inventorymanagement.model.DeliveryStatus;
import com.shopify.intern.challenge.inventorymanagement.model.InventoryItem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import javax.print.attribute.standard.Destination;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InventoryManagementApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port + "/api/v1";
    }

    /**
     * Test Get All Inventory items
     */
    @Test
    public void testGetAllInventoryItems() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/item?pageSize=5&pageNo=0",
                HttpMethod.GET, entity, String.class);
        Assert.assertNotNull(response.getBody());
    }

    /**
     * Test add Inventory Item url
     * @throws ParseException
     */
    @Test
    public void testCreateItem() throws ParseException {
        InventoryItem item = new InventoryItem();
        item.setDescription("Test item 3");
        item.setSourceName("Source1");
        item.setDestinationName("Destination A");
        item.setItemCost(12);
        item.setCustomDuty(12);
        item.setPickUpDate(new SimpleDateFormat("yyyy-mm-dd").parse("2020-12-12"));
        item.setEstimatedDeliveryDate(new SimpleDateFormat("yyyy-mm-dd").parse("2019-12-12"));
        item.setStatus(DeliveryStatus.DELIVERED);
        ResponseEntity<InventoryItem> postResponse = restTemplate.postForEntity(getRootUrl() + "/item", item, InventoryItem.class);
        Assert.assertNotNull(postResponse.getStatusCode() == HttpStatus.OK);
    }

    /**
     * Test Edit URL
     * @throws ParseException
     */
    @Test
    public void testEditInventoryItems() throws ParseException {
        InventoryItem item = new InventoryItem();
        Long id = Long.valueOf(1);
        item.setId(id);
        item.setDescription("Test item 3");
        item.setSourceName("Source1");
        item.setDestinationName("Destination A");
        item.setItemCost(12);
        item.setCustomDuty(12);
        item.setPickUpDate(new SimpleDateFormat("yyyy-mm-dd").parse("2020-12-12"));
        item.setEstimatedDeliveryDate(new SimpleDateFormat("yyyy-mm-dd").parse("2019-12-12"));
        item.setStatus(DeliveryStatus.DELIVERED);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(item.toString(), headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/item/" + 1, HttpMethod.PUT, entity, String.class, id);
        Assert.assertNotNull(response.getStatusCode() == HttpStatus.OK);

    }

    /**
     * Test Delete Url
     */
    @Test
    public void testDeleteInventoryItem() {
        int id = 1;
        restTemplate.delete(getRootUrl() + "/item/" + id);

        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<String>(null, headers);

            ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/item?pageSize=5&pageNo=0",
                    HttpMethod.GET, entity, String.class);
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

}