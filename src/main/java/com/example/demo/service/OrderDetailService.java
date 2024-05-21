package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Order_detail;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Products;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.service.imp.OrderDetailServiceImp;

@Service
public class OrderDetailService implements OrderDetailServiceImp {

    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Override
    public boolean insertOrderDetail(int order_id, int product_id, int quantity, int price) {
        Order_detail temp = new Order_detail();
        Orders orders = new Orders();
        Products products = new Products();
        orders.setOrder_id(order_id);
        products.setProduct_id(product_id);
        temp.setOrders(orders);
        temp.setProducts(products);
        temp.setQuantity(quantity);
        temp.setPrice(price);
        orderDetailRepository.save(temp);
        return true;
    }

    
}
