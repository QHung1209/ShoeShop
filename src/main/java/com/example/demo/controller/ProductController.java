package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.payload.ResponseData;
import com.example.demo.service.imp.ProductServiceImp;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin("*")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductServiceImp productServiceImp;

    @GetMapping("/allproduct")
    public ResponseEntity<?> getProduct() {

        ResponseData responseData = new ResponseData();
        responseData.setData(productServiceImp.getAllProduct());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/allstylename")
    public ResponseEntity<?> getStyleName() {

        ResponseData responseData = new ResponseData();
        responseData.setData(productServiceImp.getStyle());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/allcategoryname")
    public ResponseEntity<?> getCategoryName() {

        ResponseData responseData = new ResponseData();
        responseData.setData(productServiceImp.getCategory());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/allmaterialname")
    public ResponseEntity<?> getMaterialName() {

        ResponseData responseData = new ResponseData();
        responseData.setData(productServiceImp.getMaterial());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> list(@RequestParam(value = "style", required = false) String style,
            @RequestParam(value = "material", required = false) String material,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "price", required = false) String price) {
        ResponseData responseData = new ResponseData();
        List<String> styles = null;

        if (style != null) {
            styles = new ArrayList<>(Arrays.asList(style.split(",")));
        }
        List<String> materials = null;
        if (material != null) {
            materials = new ArrayList<>(Arrays.asList(material.split(",")));
        }
        List<String> categories = null;
        if (category != null) {
            categories = new ArrayList<>(Arrays.asList(category.split(",")));
        }
        List<String> prices = null;
        if(price!=null)
        {
            prices = new ArrayList<>(Arrays.asList(price.split(",")));
        }
        responseData.setData(productServiceImp.filter(styles, materials, categories, prices));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}