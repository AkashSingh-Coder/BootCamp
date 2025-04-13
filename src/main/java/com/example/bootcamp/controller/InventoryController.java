package com.example.bootcamp.controller;

import com.example.bootcamp.Entity.Inventory;
import com.example.bootcamp.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/inventories")
@RestController
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        Inventory createdInventory =inventoryService.createInventory(inventory);
        return new ResponseEntity<>(createdInventory, HttpStatus.CREATED);
    }

    @GetMapping("/{sku}")
    public ResponseEntity<?> getInventory(@PathVariable String sku) {
        Inventory inventory=inventoryService.getInventoryBySku(sku);
        if(inventory==null) {
            return new ResponseEntity<>("Inventory not found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @GetMapping
    public List<Inventory> getAllInventory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return inventoryService.getAllInventories(page, size);
    }

    @PutMapping("/{sku}")
    public ResponseEntity<?> updateInventory(
            @PathVariable String sku,
            @RequestBody Inventory inventory
    ) {
        Inventory updatedInventory = inventoryService.updateInventory(sku, inventory);
        if (updatedInventory != null) {
            return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Inventory not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<?> deleteInventory(@PathVariable String sku) {
        String response=inventoryService.deleteInventory(sku);
        if (response.equals("Inventory deleted")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error deleting inventory", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
