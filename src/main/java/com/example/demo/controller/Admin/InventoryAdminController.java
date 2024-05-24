package com.example.demo.controller.Admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Admin.InventoryAdminDTO;
import com.example.demo.entity.Inventory;
import com.example.demo.payload.ResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.demo.service.Admin.imp.InventoryAdminServiceImp;

@CrossOrigin("*")
@RestController

@RequestMapping("/admin/product")
public class InventoryAdminController {

    @Autowired
    private InventoryAdminServiceImp inventoryAdminServiceImp;

    // Tạo dữ liệu shoes table (chức năng thêm giá giày)
    // @PostMapping("/addInventory")
    // public ResponseEntity<?> createShoes(
    // @RequestParam String name,
    // @RequestParam int price) {
    // // TODO: process POST request
    // ResponseData responseData = new ResponseData();
    // boolean isSuccess = shoesAdminServiceImp.insertShoes(name, price);
    // responseData.setData(isSuccess);
    // return new ResponseEntity<>(HttpStatus.OK);
    // }

    // Lấy dữ liệu từ inventory table (hiển thị dữ liệu kho)
    @GetMapping("/getInventory")
    public ResponseEntity<?> getInventory() {
        // TODO: process POST request
        ResponseData responseData = new ResponseData();
        responseData.setData(inventoryAdminServiceImp.getInventory());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/addInventory")
    public Inventory addInventory(@RequestBody InventoryAdminDTO addInventoryDTO) {
        return inventoryAdminServiceImp.addInventory(addInventoryDTO);
    }


    @CrossOrigin(origins = "http://127.0.0.1:8080")
    @PutMapping("/updateInventory/{inventoryId}")
    public Inventory updateInventory(@PathVariable int inventoryId, @RequestBody InventoryAdminDTO updateInventoryDTO) {
        return inventoryAdminServiceImp.updateInventory(inventoryId, updateInventoryDTO);
    }

}
