package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entity.OrderDetail;
import com.example.demo.repository.CartRepository;
import com.example.demo.service.OrderDetailService;
import com.example.demo.util.error.IdInvalidException;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin("*")
@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {

    private final CartRepository cartRepository;

    private final OrderDetailService orderDetailService;

    public OrderDetailController(CartRepository cartRepository, OrderDetailService orderDetailService) {
        this.cartRepository = cartRepository;
        this.orderDetailService = orderDetailService;
    }

    @PostMapping("/orderdetails")
    public ResponseEntity<?> insertCart(@Valid @RequestBody OrderDetail orderDetail) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.orderDetailService.createOrderDetail(orderDetail));
    }

    @DeleteMapping("/orderdetails/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable("id") long id) throws IdInvalidException {
        if (this.orderDetailService.getOrderDetail(id) == null)
            throw new IdInvalidException("OrderDetail id = " + id + " doesn't exist.");
        this.orderDetailService.deleteOrderDetail(id);
        return ResponseEntity.ok(null);
    }
}
