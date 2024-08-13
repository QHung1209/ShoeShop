package com.example.demo.domain.response.orderdetail;

import com.example.demo.domain.response.product.ResGetProductDTO;

public class ResGetOrderDetailDTO {
    private long id;
    
    private ResGetProductDTO product;

    private int quantity;

    private int size;

    public ResGetOrderDetailDTO(long id, ResGetProductDTO product, int quantity, int size) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.size = size;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ResGetProductDTO getProduct() {
        return product;
    }

    public void setProduct(ResGetProductDTO product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    
}
