package com.example.bootcamp.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
public class Inventory {

    @Id
    private String sku;
    private String type;
    private String status;
    private String primaryLocation;

    private String vin;
    private String make;
    private String model;
    private String trim;
    private int year;

    private double costPrice;
    private double sellingPrice;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String createdBy;
    private String updatedBy;

}