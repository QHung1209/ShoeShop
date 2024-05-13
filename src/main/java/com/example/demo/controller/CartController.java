package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payload.ResponseData;
import com.example.demo.service.imp.CartServiceImp;
import com.example.demo.utils.JwtUtilsHelper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@CrossOrigin("*")
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartServiceImp cartServiceImp;

    @Autowired
    JwtUtilsHelper jwtUtilsHelper;

    
    @GetMapping("/getAllCarts")
    public ResponseEntity<?> getCart() {

        ResponseData responseData = new ResponseData();
        responseData.setData(cartServiceImp.getAllCart());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/insertCart")
    public ResponseEntity<?> insertCart(@RequestParam int user_id,
            @RequestParam int product_id,
            @RequestParam int size_id,
            @RequestParam int quantity) {
        ResponseData responseData = new ResponseData();
        responseData.setData(cartServiceImp.insertCart(user_id, product_id, size_id, quantity));
        return new ResponseEntity<>(responseData, HttpStatus.OK);

    }

    @DeleteMapping("/deleteCart")
    public ResponseEntity<?> deleteCart(@RequestParam int user_id,
            @RequestParam int product_id,
            @RequestParam int size_id) {
        ResponseData responseData = new ResponseData();
        responseData.setData(cartServiceImp.deleteCart(user_id, product_id, size_id));
        return null;
    }

}
