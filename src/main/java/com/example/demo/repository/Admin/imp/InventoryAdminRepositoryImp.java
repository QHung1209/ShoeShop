package com.example.demo.repository.Admin.imp;

import java.util.List;
import com.example.demo.dto.Admin.InventoryAdminDTO;
import com.example.demo.entity.Inventory;

public interface InventoryAdminRepositoryImp {

    public List<InventoryAdminDTO> getInventory();

    public Inventory addInventory(String shoeName, int sizeName, int quantity);

    public Inventory updateInventory(int inventoryId, int quantity);

}
