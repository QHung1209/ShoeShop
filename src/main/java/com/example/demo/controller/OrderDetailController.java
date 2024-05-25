package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payload.ResponseData;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.imp.OrderDetailServiceImp;
import com.example.demo.utils.JwtUtilsHelper;

import org.springframework.web.bind.annotation.PostMapping;

@CrossOrigin("*")
@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {
    @Autowired
    OrderDetailServiceImp orderDetailServiceImp;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    JwtUtilsHelper jwtUtilsHelper;

    @PostMapping("/insertOrderDetail")
    public ResponseEntity<?> insertCart(@RequestParam int user_id,
            @RequestParam int product_id,
            @RequestParam int size_id,
            @RequestParam int quantity,
            @RequestParam int price) {
        int order_id = orderRepository.findMaxOrderId(user_id);
        ResponseData responseData = new ResponseData();
        responseData.setData(orderDetailServiceImp.insertOrderDetail(order_id, product_id, size_id, quantity, price));
        cartRepository.deleteAllCart(user_id);
        return new ResponseEntity<>(responseData, HttpStatus.OK);

    }
}
