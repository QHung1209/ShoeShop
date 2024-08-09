package com.example.demo.domain.dto;

public class ImageDTO {
    private int image_id;
    private String image_url;
    private int product_id;
    public int getImage_id() {
        return image_id;
    }
    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }
    public String getImage_url() {
        return image_url;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
    public int getProduct_id() {
        return product_id;
    }
    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
