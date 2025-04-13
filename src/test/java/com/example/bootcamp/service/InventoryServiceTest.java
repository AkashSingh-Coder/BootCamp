package com.example.bootcamp.service;

import com.example.bootcamp.repository.InventoryRepository;
import com.example.bootcamp.Entity.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
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

        Inventory inventory = new Inventory();
        inventory.setSku("12345");
        inventory.setSellingPrice(1000.0);


        when(inventoryRepository.save(inventory)).thenReturn(inventory);


        Inventory result = inventoryService.createInventory(inventory);


        assertNotNull(result);
        assertEquals(1000.0, result.getSellingPrice());


        verify(inventoryRepository, times(1)).save(inventory);
    }

    @Test
    void getAllInventories() {
        Inventory inventory1 = new Inventory();
        inventory1.setSku("12345");
        inventory1.setSellingPrice(1000.0);

        Inventory inventory2 = new Inventory();
        inventory2.setSku("67890");
        inventory2.setSellingPrice(2000.0);

        when(inventoryRepository.findAll(PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(List.of(inventory1, inventory2)));

        List<Inventory> result = inventoryService.getAllInventories(0, 10);


        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("12345", result.get(0).getSku());
        assertEquals("67890", result.get(1).getSku());


        verify(inventoryRepository, times(1)).findAll(PageRequest.of(0, 10));
    }

    @Test
    void updateInventory() {

    }

    @Test
    void deleteInventory() {
    }
}