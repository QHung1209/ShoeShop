package com.example.demo.dto;

import com.example.demo.entity.Products;

public class ProductDTO {

    private Products product_id;
    private String shoe_name;
    private String color;
    private int size;
    private String gender;
    private String style;
    private String material;
    private String category;
    private int quantity;
    private int discount;
    private int price;
    private String image_url;
    public Products getProduct_id() {
        return product_id;
    }
    public void setProduct_id(Products product_id) {
        this.product_id = product_id;
    }
    public String getShoe_name() {
        return shoe_name;
    }
    public void setShoe_name(String shoe_name) {
        this.shoe_name = shoe_name;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    
    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getDiscount() {
        return discount;
    }
    public void setDiscount(int discount) {
        this.discount = discount;
    }
    public String getImage_url() {
        return image_url;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getStyle() {
        return style;
    }
    public void setStyle(String style) {
        this.style = style;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

}
