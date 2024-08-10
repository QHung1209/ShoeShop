package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.OrderDetail;
import com.example.demo.repository.OrderDetailRepository;

@Service
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    

    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
        return this.orderDetailRepository.save(orderDetail);
    }

    
}
