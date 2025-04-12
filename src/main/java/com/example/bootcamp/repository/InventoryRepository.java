package com.example.bootcamp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bootcamp.Entity.Inventory;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findBySku(String sku);
}