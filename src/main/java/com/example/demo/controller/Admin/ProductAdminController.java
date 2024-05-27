package com.example.demo.controller.Admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Products;
import com.example.demo.payload.ResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PostMapping("/addProduct")
    public Products createProduct(
            @RequestParam String shoeName,
            @RequestParam String colorName,
            @RequestParam String genderName,
            @RequestParam String styleName,
            @RequestParam String materialName,
            @RequestParam String categoryName,
            @RequestParam int discount,
            @RequestParam MultipartFile imageFile) {
        return productAdminServiceImp.addProduct(shoeName, colorName, genderName, styleName, materialName, categoryName,
                discount, imageFile);
    }

    // Cập nhất giá trị sản phẩm

    @PutMapping("/updateProduct/{productId}")
    public Products updateProduct(
            @PathVariable int productId,
            @RequestParam(required = false) String shoeName,
            @RequestParam(required = false) String colorName,
            @RequestParam(required = false) String genderName,
            @RequestParam(required = false) String styleName,
            @RequestParam(required = false) String materialName,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) Integer discount,
            @RequestParam(required = false) MultipartFile imageFile) {
        return productAdminServiceImp.updateProduct(productId, shoeName, colorName, genderName, styleName, materialName,
                categoryName, discount, imageFile);
    }
    // Xóa sản phẩmáđááđâsdáaaaaâ

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

    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(
            @RequestParam("image-file1") MultipartFile file1,
            @RequestParam("image-file2") MultipartFile file2,
            @RequestParam("image-file3") MultipartFile file3,
            @RequestParam("image-file4") MultipartFile file4,
            @RequestParam("product-id") int productId) {
        try {
            productAdminServiceImp.saveImage(file1, productId);
            productAdminServiceImp.saveImage(file2, productId);
            productAdminServiceImp.saveImage(file3, productId);
            productAdminServiceImp.saveImage(file4, productId);

            return ResponseEntity.ok("Tải lên hình ảnh thành công!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Có lỗi xảy ra khi tải lên hình ảnh!");
        }
    }

}
