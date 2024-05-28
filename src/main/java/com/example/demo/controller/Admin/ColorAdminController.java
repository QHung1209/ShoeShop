package com.example.demo.controller.Admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payload.ResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.service.Admin.imp.ColorsAdminServiceImp;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.List;

@CrossOrigin("*")
@RestController

@RequestMapping("/admin/product")
public class ColorAdminController {

    @Autowired
    private ColorsAdminServiceImp colorsAdminServiceImp;

    // Tạo dữ liệu colors table (Chức năng thêm màu)
    @PostMapping("/addColors")
    public ResponseEntity<?> createColor(@RequestParam String color_code, @RequestParam String color_name) {
        // TODO: process POST request
        ResponseData responseData = new ResponseData();
        boolean isSuccess = colorsAdminServiceImp.insertColors(color_code, color_name);
        responseData.setData(isSuccess);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Lấy dữ liệu từ colors table
    @GetMapping("/getColors")
    public ResponseEntity<?> getColors() {
        // TODO: process POST request
        ResponseData responseData = new ResponseData();
        responseData.setData(colorsAdminServiceImp.getColors());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    // Cập nhật dữ liệu từ colors table
    @PutMapping("/updateColorName")
    public ResponseEntity<Void> updateColorName(@RequestParam String color_name, @RequestParam String newColorName) {
        colorsAdminServiceImp.updateColorName(color_name, newColorName);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/updateColorCode")
    public ResponseEntity<Void> updateColorCode(@RequestParam String color_code, @RequestParam String newColorCode) {
        colorsAdminServiceImp.updateColorCode(color_code, newColorCode);
        return ResponseEntity.ok().build();
    }

    // Kiểm tra color_name có tồn tại không
    @GetMapping("/checkColorNameExists")
    public ResponseEntity<?> checkColorNameExists(@RequestParam String color_name) {
        boolean exists = colorsAdminServiceImp.checkColorNameExists(color_name);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    // Kiểm tra color_code có tồn tại không
    @GetMapping("/checkColorCodeExists")
    public ResponseEntity<?> checkColorCodeExists(@RequestParam String color_code) {
        boolean exists = colorsAdminServiceImp.checkColorCodeExists(color_code);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    // Lấy tất cả color_name từ colors table
    @GetMapping("/getColorsNames")
    public List<String> getAllColorName() {
        return colorsAdminServiceImp.getAllColorName();
    }
}
