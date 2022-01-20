package com.shopify.intern.challenge.inventorymanagement.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "Inventory")
@EntityListeners(AuditingEntityListener.class)
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "itemCost", nullable = false)
    private Integer itemCost;

    @Column(name = "customDuty", nullable = false)
    private Integer customDuty;

    @Column(name = "sourceName", nullable = false)
    private String sourceName;

    @Column(name = "destinationName", nullable = false)
    private String destinationName;

    @Temporal(TemporalType.DATE)
    @Column(name = "pickUpDate", nullable = false)
    private Date pickUpDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "estimatedDeliveryDate", nullable = false)
    private Date estimatedDeliveryDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "actualDeliveryDate", nullable = true)
    private Date actualDeliveryDate;

    @Column(name = "status", nullable = false)
    private DeliveryStatus status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getItemCost() {
        return itemCost;
    }

    public void setItemCost(Integer itemCost) {
        this.itemCost = itemCost;
    }

    public Integer getCustomDuty() {
        return customDuty;
    }

    public void setCustomDuty(Integer customDuty) {
        this.customDuty = customDuty;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public Date getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(Date estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public Date getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(Date actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id +
                "," + description  +
                "," + itemCost +
                "," + customDuty +
                "," + sourceName +
                "," + destinationName +
                "," + pickUpDate +
                "," + estimatedDeliveryDate +
                "," + actualDeliveryDate +
                "," + status;
    }
}
