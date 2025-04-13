package com.example.bootcamp.repository;

import com.example.bootcamp.Entity.Inventory;
import org.junit.Test;

import static org.junit.Assert.*;

public class InventoryRepositoryTest {

    @Test
    public void findBySku() {
        Inventory inventory = new Inventory();
        inventory.setSku("1");
        assertEquals("1", inventory.getSku());
    }
}