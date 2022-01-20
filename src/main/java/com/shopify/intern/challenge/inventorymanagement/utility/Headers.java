package com.shopify.intern.challenge.inventorymanagement.utility;

public enum Headers {
    ID("Id"),
    DESCRIPTION("Description"),
    ITEM_COST("Item Cost"),
    CUSTOM_DUTY("Custom Duty"),
    SOURCE_NAME("Source Name"),
    DESTINATION_NAME("Destination Name"),
    PICK_UP_DATE("Pick Up Date"),
    ESTIMATED_DELIVERY_DATE("Estimated Delivery Date"),
    ACTUAL_DELIVERY_DATE("Actual Delivery Date"),
    STATUS("Status");

    private String code;

    private Headers(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
