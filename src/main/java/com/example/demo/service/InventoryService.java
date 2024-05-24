package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.InventoryReposity;
import com.example.demo.service.imp.InventoryImp;

@Service
public class InventoryService implements InventoryImp {

    @Autowired
    InventoryReposity inventoryReposity;

    @Override
    public int Quantity(int product_id, int size_id)
    {
        return inventoryReposity.Quantity(product_id, size_id);
    }
}
