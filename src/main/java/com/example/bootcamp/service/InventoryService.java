package com.example.bootcamp.service;

import com.example.bootcamp.repository.InventoryRepository;
import com.example.bootcamp.Entity.Inventory;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }


    public Inventory getInventoryBySku(String sku) {
        Optional<Inventory> inventory = inventoryRepository.findBySku(sku);
        return inventory.orElse(null);
    }

    public Inventory createInventory(Inventory inventory) {
        if (inventory.getSellingPrice() <= 0) {
            throw new IllegalArgumentException("Selling price must be greater than 0");
        }
        return inventoryRepository.save(inventory);
    }

    public List<Inventory> getAllInventories(int page, int size) {

        return inventoryRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public Inventory updateInventory(String sku, Inventory inventory) {
        Optional<Inventory> existingInventory = inventoryRepository.findBySku(sku);
        if (existingInventory.isPresent()) {
            if (inventory.getSellingPrice() <= 0) {
                throw new IllegalArgumentException("Selling price must be greater than 0");
            }
            Inventory updatedInventory = existingInventory.get();
            updatedInventory.setType(inventory.getType());
            updatedInventory.setStatus(inventory.getStatus());
            updatedInventory.setPrimaryLocation(inventory.getPrimaryLocation());
            updatedInventory.setVin(inventory.getVin());
            updatedInventory.setMake(inventory.getMake());
            updatedInventory.setModel(inventory.getModel());
            updatedInventory.setTrim(inventory.getTrim());
            updatedInventory.setYear(inventory.getYear());
            updatedInventory.setCostPrice(inventory.getCostPrice());
            updatedInventory.setSellingPrice(inventory.getSellingPrice());
            updatedInventory.setUpdatedAt(inventory.getUpdatedAt());
            updatedInventory.setUpdatedBy(inventory.getUpdatedBy());
            return inventoryRepository.save(updatedInventory);
        }
        return null;
    }
    final static String STATUS="Inventory deleted";
    final static String NOT_FOUND="Inventory not found";
    public String deleteInventory(String sku) {
        Optional<Inventory> inventory = inventoryRepository.findBySku(sku);
        if (inventory.isPresent()) {
            inventoryRepository.delete(inventory.get());
            return STATUS;
        }
        return NOT_FOUND;
    }
}

