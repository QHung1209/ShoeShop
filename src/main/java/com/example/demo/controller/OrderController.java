package com.example.demo.controller;

import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payload.ResponseData;
import com.example.demo.service.imp.OrderServiceImp;
import com.example.demo.utils.JwtUtilsHelper;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;


@CrossOrigin("*")
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderServiceImp orderServiceImp;
    @Autowired
    JwtUtilsHelper jwtUtilsHelper;

    @PostMapping("/insertOrder")
    public ResponseEntity<?> insertOrder(@RequestParam int user_id,
            @RequestParam String address,
            @RequestParam String name,
            @RequestParam String telephone,
            @RequestParam double total_amount,
            @RequestParam String date_order) {
        ResponseData responseData = new ResponseData();
        long timestampLong = Long.parseLong(date_order);

        Timestamp timestamp = new Timestamp(timestampLong);
        responseData.setData(orderServiceImp.insertOrder(user_id, address,name, telephone, total_amount, timestamp));
        return new ResponseEntity<>(responseData, HttpStatus.OK);

    }

    @GetMapping("/getAllOrder")
    public ResponseEntity<?> findAllOrder(@RequestParam int user_id) {
        ResponseData responseData = new ResponseData();
        responseData.setData(orderServiceImp.findAllOrderByUserId(user_id));
        return new ResponseEntity<>(responseData, HttpStatus.OK);

    }
    
    
}
