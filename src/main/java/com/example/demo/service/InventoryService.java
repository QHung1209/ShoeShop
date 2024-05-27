package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.InventoryDTO;
import com.example.demo.entity.Inventory;
import com.example.demo.repository.InventoryReposity;
import com.example.demo.service.imp.InventoryImp;

@Service
public class InventoryService implements InventoryImp {

    @Autowired
    InventoryReposity inventoryReposity;

    @Override
    public int Quantity(int product_id, int size_id) {
        return inventoryReposity.Quantity(product_id, size_id);
    }

    static public InventoryDTO getInventoryDTO(Inventory inv) {
        InventoryDTO temp = new InventoryDTO();
        temp.setInventory_id(inv.getInventory_id());
        temp.setProduct_id(inv.getProducts().getProduct_id());
        temp.setSize_id(inv.getSizes().getSize_id());
        temp.setSize_name(inv.getSizes().getSize_name());
        temp.setQuantity(inv.getQuantity());
        return temp;
    }
}
