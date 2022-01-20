package com.shopify.intern.challenge.inventorymanagement.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class DeliveryStatusConverter implements AttributeConverter<DeliveryStatus, String> {
    @Override
    public String convertToDatabaseColumn(DeliveryStatus status) {
        if (status == null) {
            return null;
        }
        return status.getCode();
    }

    @Override
    public DeliveryStatus convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(DeliveryStatus.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
