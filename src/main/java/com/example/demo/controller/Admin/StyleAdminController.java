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
import com.example.demo.service.Admin.imp.StylesAdminServiceImp;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

@CrossOrigin("*")
@RestController

@RequestMapping("/admin/product")
public class StyleAdminController {

    @Autowired
    private StylesAdminServiceImp stylesAdminServiceImp;

    // #Styles

    // Tạo dữ liệu styles table
    @PostMapping("/addStyles")
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

    // Cập nhật dữ liệu từ styles table
    @PutMapping("/updateStyleName")
    public ResponseEntity<Void> updateStyleName(@RequestParam String style_name, @RequestParam String newStyleName) {
        return stylesAdminServiceImp.updateStyleName(style_name, newStyleName);
    }

    // Xóa dữ liệu từ styles table
    @DeleteMapping("/deleteStyles/{style_name}")
    public ResponseEntity<Void> deleteStyleByName(@PathVariable String style_name) {
        return stylesAdminServiceImp.deleteStyleByName(style_name);
    }

    // Kiểm tra style_name có tồn tại không
    @GetMapping("/checkStyleExists")
    public ResponseEntity<?> checkStyleExists(@RequestParam String style_name) {
        boolean exists = stylesAdminServiceImp.checkStyleExists(style_name);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    // Lấy tất cả style_name từ sizes table
    @GetMapping("/getStylesNames")
    public List<String> getAllStyleName() {
        return stylesAdminServiceImp.getAllStyleName();
    }

}
