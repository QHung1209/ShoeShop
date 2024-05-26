package com.example.demo.service.Admin.imp;

import java.util.List;
import com.example.demo.entity.Orders;
import com.example.demo.dto.Admin.OrderDetailAdminDTO;


public interface OrdersAdminServiceImp {
    List<Orders> getOrders();

    public void updateOrderStatusAndInventory(int order_id);

    public List<OrderDetailAdminDTO> getOrderDetailsByOrderId(int orderId);
}
