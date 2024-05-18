package com.example.demo.service.imp;

import java.sql.Timestamp;
public interface OrderServiceImp {
    boolean insertOrder(int user_id, String address, String telephone,double total_amount, Timestamp date_order);
}
