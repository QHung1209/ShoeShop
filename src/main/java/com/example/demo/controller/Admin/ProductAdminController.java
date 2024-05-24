package com.example.demo.controller.Admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.payload.ResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.service.Admin.imp.FileAdminServiceImp;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.demo.service.Admin.imp.ProductAdminServiceImp;

@CrossOrigin("*")
@RestController

@RequestMapping("/admin/product")
public class ProductAdminController {

    @Autowired
    private FileAdminServiceImp fileAdminServiceImp;

    @Autowired
    private ProductAdminServiceImp productAdminServiceImp;

    // PRODUCT
    // Xuất thông tin sản phẩm
    @GetMapping("/getProduct")
    public ResponseEntity<?> getProduct() {
        // TODO: process POST request
        ResponseData responseData = new ResponseData();
        responseData.setData(productAdminServiceImp.getAllProducts());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createProduct(@RequestParam MultipartFile file) {
        // TODO: process POST request
        ResponseData responseData = new ResponseData();
        boolean isSuccess = fileAdminServiceImp.saveFile(file);
        responseData.setData(isSuccess);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<?> getFileProduct(@PathVariable String filename) {
        // TODO: process POST request
        Resource resource = fileAdminServiceImp.loadFile(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }

    // Cập nhất giá trị sản phẩm

    // Xóa sản phẩm

    // // GENDER

    // // Tạo dữ liệu
    // @PostMapping("/genders")
    // public ResponseEntity<?> createGender(@RequestParam MultipartFile file,
    // @RequestParam String genderName) {
    // // TODO: process POST request
    // ResponseData responseData = new ResponseData();
    // boolean isSuccess = fileAdminServiceImp.saveFile(file);
    // responseData.setData(isSuccess);
    // return new ResponseEntity<>(HttpStatus.OK);
    // }

}
