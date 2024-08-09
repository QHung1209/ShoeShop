package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Inventory;
import com.example.demo.repository.InventoryRepository;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory createInventory(Inventory inventory) {
        return this.inventoryRepository.save(inventory);
    }

    public Inventory getInventoryById(long id) {
        Optional<Inventory> inventoryOptional = this.inventoryRepository.findById(id);
        return inventoryOptional.isPresent() ? inventoryOptional.get() : null;
    }

    public Inventory updateInventory(Inventory inventory) {
        Inventory update = this.getInventoryById(inventory.getId());
        if (inventory.getProduct() != null)
            update.setProduct(inventory.getProduct());
        if (inventory.getSize() != null)
            update.setSize(inventory.getSize());
        update.setQuantity(inventory.getQuantity());

        update = this.inventoryRepository.save(update);
        return update;
    }

    public void deleteInventory(long id) {
        this.inventoryRepository.deleteById(id);
    }
}
