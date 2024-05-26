package com.example.demo.dto;

import java.util.List;


public class ProductDTO {

    private int product_id;
    private String shoe_name;
    private String color_code;
    private String color_name;
    private String gender;
    private String style;
    private String material;
    private String category;
    private int discount;
    private List<ProductDTO> related_products;
    private List<InventoryDTO> inventoryDTOs;
    private List<ImageDTO> imageDTOs;
    private int price;
    private String image_url;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getShoe_name() {
        return shoe_name;
    }

    public void setShoe_name(String shoe_name) {
        this.shoe_name = shoe_name;
    }

    public String getColor_name() {
        return color_name;
    }

    public void setColor_name(String color_name) {
        this.color_name = color_name;
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

    public String getColor_code() {
        return color_code;
    }

    public void setColor_code(String color_code) {
        this.color_code = color_code;
    }

    public List<ProductDTO> getRelated_products() {
        return related_products;
    }

    public void setRelated_products(List<ProductDTO> related_products) {
        this.related_products = related_products;
    }

    public List<InventoryDTO> getInventoryDTOs() {
        return inventoryDTOs;
    }

    public void setInventoryDTOs(List<InventoryDTO> inventoryDTOs) {
        this.inventoryDTOs = inventoryDTOs;
    }

    public List<ImageDTO> getImageDTOs() {
        return imageDTOs;
    }

    public void setImageDTOs(List<ImageDTO> imageDTOs) {
        this.imageDTOs = imageDTOs;
    }


}
