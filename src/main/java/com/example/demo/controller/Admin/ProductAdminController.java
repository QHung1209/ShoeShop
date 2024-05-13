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
import com.example.demo.service.Admin.imp.ShoesAdminServiceImp;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpHeaders;
import com.example.demo.service.Admin.imp.ColorsAdminServiceImp;
import com.example.demo.service.Admin.imp.SizesAdminServiceImp;
import com.example.demo.service.Admin.imp.MaterialsAdminServiceImp;
import com.example.demo.service.Admin.imp.StylesAdminServiceImp;
import com.example.demo.service.Admin.imp.CategoriesAdminServiceImp;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.example.demo.entity.Shoes;
import java.util.Optional;

@CrossOrigin("*")
@RestController

@RequestMapping("/admin/product")
public class ProductAdminController {

    @Autowired
    private FileAdminServiceImp fileAdminServiceImp;

    @Autowired
    private ShoesAdminServiceImp shoesAdminServiceImp;

    @Autowired
    private ColorsAdminServiceImp colorsAdminServiceImp;

    @Autowired
    private SizesAdminServiceImp sizesAdminServiceImp;

    @Autowired
    private MaterialsAdminServiceImp materialsAdminServiceImp;

    @Autowired
    private StylesAdminServiceImp stylesAdminServiceImp;

    @Autowired
    private CategoriesAdminServiceImp categoriesAdminServiceImp;

    @Autowired
    private com.example.demo.repository.Admin.ShoesAdminRepository shoesRepository;

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

    // Tạo dữ liệu shoes table (chức năng thêm giá giày)
    @PostMapping("/shoes")
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

    // Cập nhật dữ liệu từ shoes table (chức năng cập nhật giày)
    @PostMapping("/updateShoes")
    public ResponseEntity<?> updateShoes(
            @RequestParam String name,
            @RequestParam int price) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Xóa dữ liệu từ shoes table (chức năng xóa giày)
    @DeleteMapping("/deleteShoes/{name}")
    public ResponseEntity<Void> deleteShoesByName(@PathVariable String name) {
        Optional<Shoes> shoesOptional = shoesRepository.findByName(name);
        if (shoesOptional.isPresent()) {
            shoesRepository.delete(shoesOptional.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Tạo dữ liệu colors table (Chức năng thêm màu)
    @PostMapping("/colors")
    public ResponseEntity<?> createColor(@RequestParam String color_name) {
        // TODO: process POST request
        ResponseData responseData = new ResponseData();
        boolean isSuccess = colorsAdminServiceImp.insertColors(color_name);
        responseData.setData(isSuccess);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Lấy dữ liệu từ colors table
    // @GetMapping("/getColors")
    // public ResponseEntity<?> getColors() {
    // // TODO: process POST request
    // ResponseData responseData = new ResponseData();
    // responseData.setData(colorsAdminServiceImp.getColors());
    // return new ResponseEntity<>(responseData, HttpStatus.OK);
    // }

    // Tạo dữ liệu sizes table
    @PostMapping("/sizes")
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

    // Xóa dữ liệu từ sizes table
    @DeleteMapping("/deleteSizes/{size_name}")
    public ResponseEntity<Void> deleteSizeByName(@PathVariable Integer size_name) {
        return sizesAdminServiceImp.deleteSizeByName(size_name);
    }

    // Tạo dữ liệu
    @PostMapping("/genders")
    public ResponseEntity<?> createGender(@RequestParam MultipartFile file, @RequestParam String genderName) {
        // TODO: process POST request
        ResponseData responseData = new ResponseData();
        boolean isSuccess = fileAdminServiceImp.saveFile(file);
        responseData.setData(isSuccess);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Tạo dữ liệu materials table
    @PostMapping("/materials")
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


    // Xóa dữ liệu từ materials table
    @DeleteMapping("/deleteMaterials/{material_name}")
    public ResponseEntity<Void> deleteMaterialByName(@PathVariable String material_name) {
        return materialsAdminServiceImp.deleteMaterialByName(material_name);
    }


    // #Styles

    // Tạo dữ liệu styles table
    @PostMapping("/styles")
    public ResponseEntity<?> createStyles(@RequestParam String style_name) {
        // TODO: process POST request
        ResponseData responseData = new ResponseData();
        boolean isSuccess = stylesAdminServiceImp.insertStyles(style_name);
        responseData.setData(isSuccess);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Lấy dữ liệu từ styles table
    @GetMapping("/getStyles")
    public ResponseEntity<?> getStyles() {
        // TODO: process POST request
        ResponseData responseData = new ResponseData();
        responseData.setData(stylesAdminServiceImp.getStyles());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    // Xóa dữ liệu từ styles table
    @DeleteMapping("/deleteStyles/{style_name}")
    public ResponseEntity<Void> deleteStyleByName(@PathVariable String style_name) {
        return stylesAdminServiceImp.deleteStyleByName(style_name);
    }

    // #Categories

    // Tạo dữ liệu categories table
    @PostMapping("/categories")
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

    // Xóa dữ liệu từ Categories table
    @DeleteMapping("/deleteCategories/{category_name}")
    public ResponseEntity<Void> deleteCategoryByName(@PathVariable String category_name) {
        return categoriesAdminServiceImp.deleteCategoryByName(category_name);
    }
}
