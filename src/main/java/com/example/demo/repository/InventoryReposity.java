package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Inventory;
import com.example.demo.entity.Products;

@Repository
public interface InventoryReposity extends JpaRepository<Inventory, Integer> {
    @Query("SELECT i.quantity FROM Inventory i WHERE i.products.product_id = :product_id AND i.sizes.size_id = :size_id")
    int Quantity(int product_id, int size_id);

    @Query("SELECT DISTINCT i.products FROM Inventory i GROUP BY(i.products) ORDER BY MIN(i.quantity) ASC LIMIT 5")
    List<Products> findMostSell();
}
