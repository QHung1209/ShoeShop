package com.example.demo.service.imp;

import java.sql.Timestamp;

public interface OrderDetailServiceImp {
    boolean insertOrderDetail(int order_id, int product_id, int quantity, int price);
}
