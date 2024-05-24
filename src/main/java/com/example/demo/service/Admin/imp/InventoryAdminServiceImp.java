package com.example.demo.service.Admin.imp;

import com.example.demo.dto.Admin.InventoryAdminDTO;
import com.example.demo.entity.Inventory;

import java.util.List;

public interface InventoryAdminServiceImp {
    public List<InventoryAdminDTO> getInventory();

    public Inventory addInventory(InventoryAdminDTO addInventoryDTO);

    public Inventory updateInventory(int inventoryId, InventoryAdminDTO updateInventoryDTO);

}
