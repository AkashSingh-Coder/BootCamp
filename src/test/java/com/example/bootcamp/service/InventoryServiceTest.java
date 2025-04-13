package com.example.bootcamp.service;

import com.example.bootcamp.repository.InventoryRepository;
import com.example.bootcamp.Entity.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {
    @Mock
    private InventoryRepository inventoryRepository;
    @InjectMocks
    private InventoryService inventoryService;
    @Test
    void getInventoryBySku() {
        Inventory inventory = new Inventory();
        inventory.setSku("12345");
        inventory.setSellingPrice(1000.0);


        when(inventoryRepository.findBySku("12345")).thenReturn(Optional.of(inventory));


        Inventory result = inventoryService.getInventoryBySku("12345");


        assertNotNull(result);
        assertEquals("12345", result.getSku());


        verify(inventoryRepository, times(1)).findBySku("12345");
    }

    @Test
    void createInventory() {
    }

    @Test
    void getAllInventories() {
    }

    @Test
    void updateInventory() {
    }

    @Test
    void deleteInventory() {
    }
}