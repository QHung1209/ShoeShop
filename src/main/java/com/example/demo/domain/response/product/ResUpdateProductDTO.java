package com.example.demo.domain.response.product;

import java.time.Instant;

import com.example.demo.domain.response.product.ResCreateProductDTO.ProductCategory;
import com.example.demo.domain.response.product.ResCreateProductDTO.ProductColor;
import com.example.demo.domain.response.product.ResCreateProductDTO.ProductMaterial;
import com.example.demo.domain.response.product.ResCreateProductDTO.ProductShoe;
import com.example.demo.domain.response.product.ResCreateProductDTO.ProductStyle;
import com.example.demo.util.constant.GenderEnum;

public class ResUpdateProductDTO {
    private long id;
    private ProductShoe shoe;
    private GenderEnum gender;
    private ProductColor color;
    private ProductMaterial material;
    private ProductStyle style;
    private ProductCategory category;
    private Instant updatedAt;
    private String updatedBy;
    private String url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductShoe getShoe() {
        return shoe;
    }

    public void setShoe(ProductShoe shoe) {
        this.shoe = shoe;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public ProductColor getColor() {
        return color;
    }

    public void setColor(ProductColor color) {
        this.color = color;
    }

    public ProductMaterial getMaterial() {
        return material;
    }

    public void setMaterial(ProductMaterial material) {
        this.material = material;
    }

    public ProductStyle getStyle() {
        return style;
    }

    public void setStyle(ProductStyle style) {
        this.style = style;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
