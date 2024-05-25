package com.example.demo.controller.Admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.service.Admin.imp.OrdersAdminServiceImp;
import com.example.demo.payload.ResponseData;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController

@RequestMapping("/admin/product")
public class OrderAdminController {

    @Autowired
    OrdersAdminServiceImp orderAdminServiceImp;

    // Lấy dữ liệu từ shoes table (hiển thị dữ liệu giày)
    @GetMapping("/getOrders")
    public ResponseEntity<?> getOrders() {
        // TODO: process POST request
        ResponseData responseData = new ResponseData();
        responseData.setData(orderAdminServiceImp.getOrders());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
