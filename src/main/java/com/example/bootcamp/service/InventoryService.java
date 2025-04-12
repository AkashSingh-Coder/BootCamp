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

    @Autowired
    private InventoryRepository inventoryRepository;

    
    public Inventory getInventoryBySku(String sku) {
        Optional<Inventory> inventory = inventoryRepository.findBySku(sku);
        return inventory.orElse(null); // Returns null if not found
    }
}

