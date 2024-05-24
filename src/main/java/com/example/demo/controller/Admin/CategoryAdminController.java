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
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.service.Admin.imp.CategoriesAdminServiceImp;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

@CrossOrigin("*")
@RestController

@RequestMapping("/admin/product")
public class CategoryAdminController {

    @Autowired
    private CategoriesAdminServiceImp categoriesAdminServiceImp;

    // #Categories

    // Tạo dữ liệu categories table
    @PostMapping("/addCategories")
    public ResponseEntity<?> createCategories(@RequestParam String category_name) {
        // TODO: process POST request
        ResponseData responseData = new ResponseData();
        boolean isSuccess = categoriesAdminServiceImp.insertCategories(category_name);
        responseData.setData(isSuccess);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Lấy dữ liệu từ Categories table
    @GetMapping("/getCategories")
    public ResponseEntity<?> getCategories() {
        // TODO: process POST request
        ResponseData responseData = new ResponseData();
        responseData.setData(categoriesAdminServiceImp.getCategories());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    // Cập nhật dữ liệu từ Categories table
    @PutMapping("/updateCategoryName")
    public ResponseEntity<Void> updateCategoryName(@RequestParam String category_name,
            @RequestParam String newCategoryName) {
        return categoriesAdminServiceImp.updateCategoryName(category_name, newCategoryName);
    }

    // Kiểm tra _name có tồn tại không
    @GetMapping("/checkCategoryExists")
    public ResponseEntity<?> checkCategoryExists(@RequestParam String category_name) {
        boolean exists = categoriesAdminServiceImp.checkCategoryExists(category_name);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    // Lay tat ca category_name tu bang category
    @GetMapping("/getCategoriesNames")
    public List<String> getCategoriesName() {
        return categoriesAdminServiceImp.getAllCategoryName();
    }

}
