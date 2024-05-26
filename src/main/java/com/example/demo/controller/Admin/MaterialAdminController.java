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
import com.example.demo.service.Admin.imp.MaterialsAdminServiceImp;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

@CrossOrigin("*")
@RestController

@RequestMapping("/admin/product")
public class MaterialAdminController {

    @Autowired
    private MaterialsAdminServiceImp materialsAdminServiceImp;

    // Tạo dữ liệu materials table
    @PostMapping("/addMaterials")
    public ResponseEntity<?> createMaterial(@RequestParam String material_name) {
        // TODO: process POST request
        ResponseData responseData = new ResponseData();
        boolean isSuccess = materialsAdminServiceImp.insertMaterials(material_name);
        responseData.setData(isSuccess);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Lấy dữ liệu từ materials table
    @GetMapping("/getMaterials")
    public ResponseEntity<?> getMaterials() {
        // TODO: process POST request
        ResponseData responseData = new ResponseData();
        responseData.setData(materialsAdminServiceImp.getMaterials());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    // Cập nhật dữ liệu từ materials table
    @PutMapping("/updateMaterialName")
    public ResponseEntity<Void> updateMaterialName(@RequestParam String material_name,
            @RequestParam String newMaterialName) {
        return materialsAdminServiceImp.updateMaterialName(material_name, newMaterialName);
    }

    // Xóa dữ liệu từ materials table
    @DeleteMapping("/deleteMaterials/{material_name}")
    public ResponseEntity<Void> deleteMaterialByName(@PathVariable String material_name) {
        return materialsAdminServiceImp.deleteMaterialByName(material_name);
    }

    // Kiểm tra material_name có tồn tại không

    @GetMapping("/checkMaterialExists")
    public ResponseEntity<?> checkMaterialExists(@RequestParam String material_name) {
        boolean exists = materialsAdminServiceImp.checkMaterialExists(material_name);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    // Lấy tất cả material_name từ sizes table
    @GetMapping("/getMaterialsNames")
    public List<String> getAllMaterialName() {
        return materialsAdminServiceImp.getAllMaterialName();
    }
}
