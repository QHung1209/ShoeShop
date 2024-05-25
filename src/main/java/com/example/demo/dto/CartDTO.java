package com.example.demo.dto;

public class CartDTO {
    private int cart_id;
    private int user_id;
    private ProductDTO productDTO;
    private int size_id;
    private int size_name;
    private int quantity;
    public int getCart_id() {
        return cart_id;
    }
    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public int getSize_id() {
        return size_id;
    }
    public void setSize_id(int size_id) {
        this.size_id = size_id;
    }
    public int getSize_name() {
        return size_name;
    }
    public void setSize_name(int size_name) {
        this.size_name = size_name;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public ProductDTO getProductDTO() {
        return productDTO;
    }
    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }
}
