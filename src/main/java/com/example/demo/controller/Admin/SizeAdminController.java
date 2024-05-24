package com.example.demo.controller.Admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.payload.ResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.service.Admin.imp.SizesAdminServiceImp;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

@CrossOrigin("*")
@RestController

@RequestMapping("/admin/product")
public class SizeAdminController {

    @Autowired
    private SizesAdminServiceImp sizesAdminServiceImp;

    // Tạo dữ liệu sizes table
    @PostMapping("/addSizes")
    public ResponseEntity<?> createSize(@RequestParam int size_name) {
        // TODO: process POST request
        ResponseData responseData = new ResponseData();
        boolean isSuccess = sizesAdminServiceImp.insertSizes(size_name);
        responseData.setData(isSuccess);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Lấy dữ liệu từ sizes table
    @GetMapping("/getSizes")
    public ResponseEntity<?> getSizes() {
        // TODO: process POST request
        ResponseData responseData = new ResponseData();
        responseData.setData(sizesAdminServiceImp.getSizes());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    // Cập nhật dữ liệu từ sizes table
    @PutMapping("/updateSizeName")
    public ResponseEntity<?> updateSizeName(@RequestParam int size_name, @RequestParam int newSizeName) {
        ResponseData responseData = new ResponseData();
        responseData.setData(sizesAdminServiceImp.updateSizeName(size_name, newSizeName));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    // Xóa dữ liệu từ sizes table
    @DeleteMapping("/deleteSizes/{size_name}")
    public ResponseEntity<Void> deleteSizeByName(@PathVariable Integer size_name) {
        return sizesAdminServiceImp.deleteSizeByName(size_name);
    }

    // Kiểm tra size_name có tồn tại không
    @GetMapping("/checkSizeExists")
    public ResponseEntity<?> checkSizeExists(@RequestParam int size_name) {
        boolean exists = sizesAdminServiceImp.checkSizeExists(size_name);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    // Lấy tất cả size_name từ sizes table
    @GetMapping("/getSizesNames")
    public List<Integer> getAllSizeName() {
        return sizesAdminServiceImp.getAllSizeName();
    }
}
