package com.example.demo.dto;

import java.sql.Timestamp;
import java.util.List;

public class OrderDTO {
    private int order_id;
    private UserDTO userDTO;
    private List<OrderDetailDTO> listOrderDetailDTOs;
    private int order_status;
    private double total_amount;
    private Timestamp date_order;
    public int getOrder_id() {
        return order_id;
    }
    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
    public UserDTO getUserDTO() {
        return userDTO;
    }
    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
    public List<OrderDetailDTO> getListOrderDetailDTOs() {
        return listOrderDetailDTOs;
    }
    public void setListOrderDetailDTOs(List<OrderDetailDTO> listOrderDetailDTOs) {
        this.listOrderDetailDTOs = listOrderDetailDTOs;
    }
    public double getTotal_amount() {
        return total_amount;
    }
    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }
    public Timestamp getDate_order() {
        return date_order;
    }
    public void setDate_order(Timestamp date_order) {
        this.date_order = date_order;
    }
    public int getOrder_status() {
        return order_status;
    }
    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }
}
