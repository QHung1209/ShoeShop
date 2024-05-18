package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Inventory  ;

@Repository
public interface InventoryReposity extends JpaRepository<Inventory, Integer> {
@Query("SELECT i.quantity FROM Inventory i WHERE i.products.product_id = :product_id AND i.sizes.size_id = :size_id")
    int Quantity(int product_id, int size_id);
}
