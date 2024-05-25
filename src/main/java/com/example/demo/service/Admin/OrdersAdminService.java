package com.example.demo.service.Admin;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Orders;
import com.example.demo.repository.Admin.imp.OrderAdminRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.service.Admin.imp.OrdersAdminServiceImp;

@Service
public class OrdersAdminService implements OrdersAdminServiceImp {

    @Autowired
    OrderAdminRepositoryImp orderAdminRepositoryImp;

    @Override
    public List<Orders> getOrders() {
        // TODO Auto-generated method stub
        List<Orders> listOrders = new ArrayList<>();

        List<Orders> listData = orderAdminRepositoryImp.findAll();

        for (Orders data : listData) {
            Orders orders = new Orders();
            orders.setName(data.getName());
            orders.setAddress(data.getAddress());
            orders.setTelephone(data.getTelephone());
            orders.setTotal_amount(data.getTotal_amount());
            orders.setDate_order(data.getDate_order());
            orders.setOrder_status(data.getOrder_status());

            listOrders.add(orders);
        }
        return listOrders;
    }
}
