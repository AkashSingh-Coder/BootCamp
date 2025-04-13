package com.example.bootcamp.controller;

import com.example.bootcamp.Entity.Inventory;
import com.example.bootcamp.enums.InventoryStatus;
import com.example.bootcamp.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InventoryControllerTest {
    @Mock
    private InventoryService inventoryService;

    @InjectMocks
    private InventoryController inventoryController;
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        Inventory inventory = new Inventory();
        inventory.setSku("12345");
        inventory.setType("Car");
        inventory.setStatus(InventoryStatus.CREATED);
        inventory.setPrimaryLocation("Gurugram, Haryana");
        inventory.setVin("1HGCM82633A123456");
        inventory.setMake("Honda");
        inventory.setModel("Civic");
        inventory.setTrim("EX");
        inventory.setYear(2022);
        inventory.setCostPrice(20000.0);
        inventory.setSellingPrice(25000.0);
        inventory.setCreatedAt(LocalDateTime.now());
        inventory.setUpdatedAt(LocalDateTime.now());
        inventory.setCreatedBy("Admin");
        inventory.setUpdatedBy("Admin");
    }

    @Test
    void createInventory() {
        Inventory inventory = new Inventory();
        when(inventoryService.createInventory(eq(inventory))).thenReturn(inventory);

        ResponseEntity<Inventory> response = inventoryController.createInventory(inventory);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(inventory, response.getBody());
        verify(inventoryService, times(1)).createInventory(eq(inventory));
    }

    @Test
    void getInventory() {
        when(inventoryService.getInventoryBySku("12345")).thenReturn(inventory);

        ResponseEntity<?> response = inventoryController.getInventory("12345");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(inventory, response.getBody());
        verify(inventoryService, times(1)).getInventoryBySku("12345");
    }

    @Test
    void getInventory_notFound() {
        when(inventoryService.getInventoryBySku("99999")).thenReturn(null);

        ResponseEntity<?> response = inventoryController.getInventory("99999");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Inventory not found", response.getBody());
        verify(inventoryService, times(1)).getInventoryBySku("99999");
    }





}