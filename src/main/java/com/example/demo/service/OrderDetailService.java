package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.OrderDetailDTO;
import com.example.demo.entity.Order_detail;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Products;
import com.example.demo.entity.Sizes;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.service.imp.OrderDetailServiceImp;

@Service
public class OrderDetailService implements OrderDetailServiceImp {

    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Override
    public boolean insertOrderDetail(int order_id, int product_id, int size_id, int quantity, int price) {
        Order_detail temp = new Order_detail();
        Orders orders = new Orders();
        Products products = new Products();
        Sizes size = new Sizes();
        size.setSize_id(size_id);
        orders.setOrder_id(order_id);
        products.setProduct_id(product_id);
        temp.setOrders(orders);
        temp.setProducts(products);
        temp.setSizes(size);
        temp.setQuantity(quantity);
        temp.setPrice(price);
        orderDetailRepository.save(temp);
        return true;
    }

    public static OrderDetailDTO getOrderDetailDTO(Order_detail odt) {
        OrderDetailDTO temp = new OrderDetailDTO();
        temp.setOrder_detail_id(odt.getOrder_detail_id());
        temp.setOrder_id(odt.getOrders().getOrder_id());
        temp.setProductDTO(ProductService.geProductDTO(odt.getProducts()));
        temp.setQuantity(odt.getQuantity());
        temp.setSize_id(odt.getSizes().getSize_id());
        temp.setSize_name(odt.getSizes().getSize_name());
        return temp;
    }
    
}
