package com.example.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.OrderDetailDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Order_detail;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Users;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.imp.OrderServiceImp;

@Service
public class OrderService implements OrderServiceImp {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Override
    public boolean insertOrder(int user_id, String address, String telephone, String name,double total_amount,
            Timestamp date_order) {
        Users user = new Users();
        user.setUser_id(user_id);
        user.setAddress(address);
        user.setTelephone(telephone);
        Orders temp = new Orders();
        temp.setName(name);
        temp.setAddress(address);
        temp.setTotal_amount(total_amount);
        temp.setUsers(user);
        temp.setTelephone(telephone);
        temp.setDate_order(date_order);
        orderRepository.save(temp);
        return true;
    }


    @Override
    public List<OrderDTO> findAllOrderByUserId(int user_id) {
        List<Orders> lOrders = orderRepository.findAllOrder(user_id);
        List<OrderDTO> lOrderDTOs = new ArrayList<>();
        
        for (Orders order : lOrders) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUser_id(user_id);
            userDTO.setAddress(order.getAddress());
            userDTO.setTelephone(order.getTelephone());
            userDTO.setName(order.getName());

            List<OrderDetailDTO> lOrderDetailDTOs = new ArrayList<>();
            List<Order_detail> lOrder_details= orderDetailRepository.findAllOrderDetail(order.getOrder_id());
            for(Order_detail odt: lOrder_details)
            {
                OrderDetailDTO temp = new OrderDetailDTO();
                temp.setOrder_detail_id(odt.getOrder_detail_id());
                temp.setOrder_id(odt.getOrders().getOrder_id());
                temp.setProductDTO(ProductService.geProductDTO(odt.getProducts()));
                temp.setQuantity(odt.getQuantity());
                lOrderDetailDTOs.add(temp);
            }

            OrderDTO temp = new OrderDTO();
            temp.setOrder_id(order.getOrder_id());
            temp.setDate_order(order.getDate_order());
            temp.setUserDTO(userDTO);
            temp.setTotal_amount(order.getTotal_amount());
            temp.setListOrderDetailDTOs(lOrderDetailDTOs);
            lOrderDTOs.add(temp);
        }
        
        return lOrderDTOs;
    }

}
