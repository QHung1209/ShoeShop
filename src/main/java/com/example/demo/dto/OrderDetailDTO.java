package com.example.demo.dto;

public class OrderDetailDTO {
    private int order_detail_id;
    private int order_id;
    private ProductDTO productDTO;
    private int size_id;
    private int size_name;


    public String getColor_name() {
        return color_name;
    }
    public void setColor_name(String color_name) {
        this.color_name = color_name;
    }
    private String color_name;
    private int quantity;
    
    public int getOrder_detail_id() {
        return order_detail_id;
    }
    public void setOrder_detail_id(int order_detail_id) {
        this.order_detail_id = order_detail_id;
    }
    public int getOrder_id() {
        return order_id;
    }
    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
    public ProductDTO getProductDTO() {
        return productDTO;
    }
    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
}
