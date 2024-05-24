package com.example.demo.controller.Admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.payload.ResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.service.Admin.imp.ShoesAdminServiceImp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

@CrossOrigin("*")
@RestController

@RequestMapping("/admin/product")
public class ShoesAdminController {

    @Autowired
    private ShoesAdminServiceImp shoesAdminServiceImp;

    // Tạo dữ liệu shoes table (chức năng thêm giá giày)
    @PostMapping("/addShoes")
    public ResponseEntity<?> createShoes(
            @RequestParam String name,
            @RequestParam int price) {
        // TODO: process POST request
        ResponseData responseData = new ResponseData();
        boolean isSuccess = shoesAdminServiceImp.insertShoes(name, price);
        responseData.setData(isSuccess);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Lấy dữ liệu từ shoes table (hiển thị dữ liệu giày)
    @GetMapping("/getShoes")
    public ResponseEntity<?> getShoes() {
        // TODO: process POST request
        ResponseData responseData = new ResponseData();
        responseData.setData(shoesAdminServiceImp.getShoes());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    // Cập nhật dữ liệu từ shoes table
    @PutMapping("/updateShoesName")
    public ResponseEntity<Void> updateShoesName(@RequestParam String name, @RequestParam String new_name) {
        return shoesAdminServiceImp.updateShoesName(name, new_name);
    }

    // Cập nhật dữ liệu từ shoes table
    @PutMapping("/updateShoesPrice")
    public ResponseEntity<Void> updateShoesPrice(@RequestParam String name, @RequestParam int new_price) {
        return shoesAdminServiceImp.updateShoesPrice(name, new_price);
    }

    // Kiểm tra name có tồn tại không
    @GetMapping("/checkShoesNameExists")
    public ResponseEntity<?> checkShoesNameExists(@RequestParam String name) {
        boolean exists = shoesAdminServiceImp.checkShoesNameExists(name);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    // Lấy tên cả giày có trong bảng shoes
    @GetMapping("/getShoesNames")
    public ResponseEntity<List<String>> getShoesNames() {
        List<String> shoesNames = shoesAdminServiceImp.getAllShoesName();
        return ResponseEntity.ok(shoesNames);
    }
}
