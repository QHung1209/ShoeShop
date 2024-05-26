package com.example.demo.controller.Admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.service.Admin.imp.OrdersAdminServiceImp;
import com.example.demo.dto.Admin.OrderDetailAdminDTO;
import com.example.demo.payload.ResponseData;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

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

    @PutMapping("/updateOrders/{orderId}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable int orderId) {
        try {
            orderAdminServiceImp.updateOrderStatusAndInventory(orderId);
            return ResponseEntity.ok("Order status updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating order status: " + e.getMessage());
        }
    }

    @GetMapping("/getOrderDetail/{orderId}")
    public ResponseEntity<List<OrderDetailAdminDTO>> getOrderDetails(@PathVariable int orderId) {
        List<OrderDetailAdminDTO> orderDetails = orderAdminServiceImp.getOrderDetailsByOrderId(orderId);
        if (orderDetails.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(orderDetails);
        }
    }

}
