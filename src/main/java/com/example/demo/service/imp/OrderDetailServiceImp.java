package com.example.demo.service.imp;

public interface OrderDetailServiceImp {
    boolean insertOrderDetail(int order_id, int product_id,int size_id, int quantity, int price);
}
