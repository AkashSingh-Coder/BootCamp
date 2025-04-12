package com.example.bootcamp.service;

import com.example.bootcamp.repository.InventoryRepository;
import com.example.bootcamp.Entity.Inventory;

import org.springframework.beans.factory.annotation.Autowired;
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

        return inventoryRepository.save(inventory);
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

