package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payload.ResponseData;
import com.example.demo.service.imp.ProductServiceImp;

@CrossOrigin("*")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductServiceImp productServiceImp;
    @GetMapping("/allproduct")
    public ResponseEntity<?> getProduct() {

        ResponseData responseData = new ResponseData();
        responseData.setData(productServiceImp.getProduct());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
