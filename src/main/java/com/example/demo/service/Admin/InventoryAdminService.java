package com.example.demo.service.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.Admin.InventoryAdminDTO;
import com.example.demo.entity.Inventory;
import com.example.demo.service.Admin.imp.InventoryAdminServiceImp;
import com.example.demo.repository.Admin.InventoryAdminRepository;
import java.util.List;

@Service
public class InventoryAdminService implements InventoryAdminServiceImp {

    @Autowired
    public InventoryAdminRepository inventoryAdminRepository;

    @Override
    public List<InventoryAdminDTO> getInventory() {
        return inventoryAdminRepository.getInventory();
    }

    @Override
    public Inventory addInventory(InventoryAdminDTO addInventoryDTO) {
        return inventoryAdminRepository.addInventory(
                addInventoryDTO.getShoeName(),
                addInventoryDTO.getSizeName(),
                addInventoryDTO.getQuantity());
    }

    @Override
    public Inventory updateInventory(int inventoryId, InventoryAdminDTO updateInventoryDTO) {
        return inventoryAdminRepository.updateInventory(
                inventoryId,
                updateInventoryDTO.getQuantity());
    }

}
