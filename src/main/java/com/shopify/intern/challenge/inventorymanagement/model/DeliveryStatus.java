package com.shopify.intern.challenge.inventorymanagement.model;

public enum DeliveryStatus {
    PICK_UP_IN_PROGRESS("PICK_UP_IN_PROGRESS"),
    PICK_UP_COMPLETE("PICK_UP_COMPLETE"),
    REACHED_WAREHOUSE("REACHED_WAREHOUSE"),
    DELVIERY_IN_PROGRESS("DELVIERY_IN_PROGRESS"),
    DELIVERED("DELIVERED");

    private String code;

    private DeliveryStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
