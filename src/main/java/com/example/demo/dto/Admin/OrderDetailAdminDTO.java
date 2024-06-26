package com.example.demo.dto.Admin;

public class OrderDetailAdminDTO {

    private int orderDetailId;

    private String shoeName;
    private String colorName;
    private int sizeName;
    private int quantity;
    private double price;

    public int getQuantity() {
        return quantity;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderDetailAdminDTO(int orderDetailId, String shoeName, int sizeName, String colorName, int quantity,
            double price) {
        this.orderDetailId = orderDetailId;
        this.shoeName = shoeName;
        this.sizeName = sizeName;
        this.colorName = colorName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getShoeName() {
        return shoeName;
    }

    public void setShoeName(String shoeName) {
        this.shoeName = shoeName;
    }

    public int getSizeName() {
        return sizeName;
    }

    public void setSizeName(int sizeName) {
        this.sizeName = sizeName;
    }

}
