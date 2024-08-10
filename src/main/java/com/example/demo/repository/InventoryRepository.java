package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.Inventory;
import com.example.demo.domain.entity.Product;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>, JpaSpecificationExecutor<Inventory> {
    // @Query("SELECT i.quantity FROM Inventory i WHERE i.products.product_id = :product_id AND i.sizes.size_id = :size_id")
    // int Quantity(int product_id, int size_id);

    // @Query("SELECT DISTINCT i.products FROM Inventory i GROUP BY(i.products) ORDER BY MIN(i.quantity) ASC LIMIT 5")
    // List<Product> findMostSell();
}
