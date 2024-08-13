package com.example.demo.domain.response.order;

import java.util.List;

import com.example.demo.domain.response.orderdetail.ResGetOrderDetailDTO;
import com.example.demo.util.constant.StatusEnum;

public class ResGetOrderDTO {
    private long id;
    private double total;
    private OrderUser user;
    private StatusEnum status;
    private List<ResGetOrderDetailDTO> orderDetail;


    public static class OrderUser {
        private long id;
        private String name;
        private String address;
        private String telephone;
        
        public OrderUser(long id, String name, String address, String telephone) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.telephone = telephone;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public double getTotal() {
        return total;
    }


    public void setTotal(double total) {
        this.total = total;
    }


    public OrderUser getUser() {
        return user;
    }


    public void setUser(OrderUser user) {
        this.user = user;
    }


    public StatusEnum getStatus() {
        return status;
    }


    public void setStatus(StatusEnum status) {
        this.status = status;
    }


    public List<ResGetOrderDetailDTO> getOrderDetail() {
        return orderDetail;
    }


    public void setOrderDetail(List<ResGetOrderDetailDTO> orderDetail) {
        this.orderDetail = orderDetail;
    }

}
