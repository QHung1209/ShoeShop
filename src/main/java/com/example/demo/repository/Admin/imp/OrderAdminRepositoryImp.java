package com.example.demo.repository.Admin.imp;

import java.util.List;


import com.example.demo.entity.Orders;

public interface OrderAdminRepositoryImp {
    List<Orders> findAll();
}
