package com.example.demo.service.imp;

import java.sql.Timestamp;
import java.util.List;

import com.example.demo.dto.OrderDTO;
public interface OrderServiceImp {
    boolean insertOrder(int user_id, String address, String name,String telephone,double total_amount, Timestamp date_order);

    public List<OrderDTO> findAllOrderByUserId(int user_id);
}
