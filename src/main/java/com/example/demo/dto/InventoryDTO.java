package com.example.demo.dto;

public class InventoryDTO {
    private int inventory_id;
    private int product_id;
    private int size_id;
    
    private int quantity;
    private int size_name;
    public int getSize_name() {
        return size_name;
    }
    public void setSize_name(int size_name) {
        this.size_name = size_name;
    }
    public int getInventory_id() {
        return inventory_id;
    }
    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
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
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
