package com.example.demo.controller;

import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entity.Order;
import com.example.demo.service.OrderDetailService;
import com.example.demo.service.OrderService;
import com.example.demo.util.constant.StatusEnum;
import com.example.demo.util.error.IdInvalidException;
import com.turkraft.springfilter.boot.Filter;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin("*")
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderDetailService orderDetailService;

    public OrderController(OrderService orderService, OrderDetailService orderDetailService) {
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
    }

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@Valid @RequestBody Order order) {

        return ResponseEntity.status(HttpStatus.CREATED).body(this.orderService.createOrder(order));

    }

    @GetMapping("/getAllOrder")
    public ResponseEntity<?> getAllOrder(@RequestBody long id, @Filter Specification<Order> specification,
            Pageable pageable) {
        return ResponseEntity.ok(this.orderService.findAllOrderByUserId(id, specification, pageable));
    }

    @DeleteMapping("orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") long id) throws IdInvalidException {
        Order order = this.orderService.getOrderById(id);
        if (order.getStatus() == StatusEnum.IN_TRANSIT || order.getStatus() == StatusEnum.RECEIVED)
            throw new IdInvalidException("Can't delete this order.");
        this.orderService.deleteOrder(id);
        return ResponseEntity.ok(null);
    }

}
