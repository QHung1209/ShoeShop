package com.example.demo.dto;

public class CartDTO {
    private int cart_id;
    private int user_id;
    private int product_id;
    private int size_id;
    private int size_name;
    private int quantity;
    private String image_url;
    private String shoe_name;
    private int price;
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
    public int getProduct_id() {
        return product_id;
    }
    public void setProduct_id(int product_id) {
        this.product_id = product_id;
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
    public String getShoe_name() {
        return shoe_name;
    }
    public void setShoe_name(String shoe_name) {
        this.shoe_name = shoe_name;
    }
    public String getImage_url() {
        return image_url;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
}
