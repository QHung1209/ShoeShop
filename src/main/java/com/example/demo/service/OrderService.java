package com.example.demo.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Orders;
import com.example.demo.entity.Users;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.imp.OrderServiceImp;

@Service
public class OrderService implements OrderServiceImp {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public boolean insertOrder(int user_id, String address,String telephone, double total_amount, Timestamp date_order) {
        Users user = new Users();
        user.setUser_id(user_id);
        user.setAddress(address);
        user.setTelephone(telephone);
        Orders temp = new Orders();
        temp.setAddress(address);
        temp.setTotal_amount(total_amount);
        temp.setUsers(user);
        temp.setDate_order(date_order);
        orderRepository.save(temp);
        return true;
    }

}
