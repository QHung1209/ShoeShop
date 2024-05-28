package com.example.demo.dto.Admin;

public class InventoryAdminDTO {
    private int inventoryId;

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    private String shoeName;
    private String colorName;
    private int sizeName;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public InventoryAdminDTO(int inventoryId, String shoeName, int sizeName, String colorName, int quantity) {
        this.inventoryId = inventoryId;
        this.shoeName = shoeName;
        this.sizeName = sizeName;
        this.colorName = colorName;
        this.quantity = quantity;
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

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }
}
