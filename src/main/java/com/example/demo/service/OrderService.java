package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Order;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    OrderDetailRepository orderDetailRepository;

    public Order createOrder(Order order) {
        return this.orderRepository.save(order);
    }

    public List<Order> findAllOrderByUserId(int user_id) {
        
        return this.findAllOrderByUserId(user_id);
    }

}
