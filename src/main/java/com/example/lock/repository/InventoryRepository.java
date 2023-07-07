package com.example.lock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lock.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long>{
  public Inventory findByProductCode(String productCode);
}
