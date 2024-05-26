package com.example.demo.service.Admin;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Orders;
import com.example.demo.repository.Admin.imp.OrderAdminRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.service.Admin.imp.OrdersAdminServiceImp;
import com.example.demo.dto.Admin.OrderDetailAdminDTO;


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
            orders.setOrder_id(data.getOrder_id());
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

    @Override
    public void updateOrderStatusAndInventory(int order_id) {
        // TODO Auto-generated method stub
        orderAdminRepositoryImp.updateOrderStatusAndInventory(order_id);
    }

    @Override
    public List<OrderDetailAdminDTO> getOrderDetailsByOrderId(int orderId) {
        // TODO Auto-generated method stub
        return orderAdminRepositoryImp.getOrderDetailsByOrderId(orderId);
    }
}
