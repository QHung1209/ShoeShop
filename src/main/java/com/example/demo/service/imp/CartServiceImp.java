package com.example.demo.service.imp;

import java.util.List;

import com.example.demo.dto.CartDTO;

public interface CartServiceImp {
    List<CartDTO> getAllCart();
    boolean insertCart(int user_id, int product_id, int size_id, int quantity);
    boolean deleteCart(int user_id, int product_id, int size_id);
}
