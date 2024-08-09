package com.example.demo.domain.response.product;

import java.time.Instant;
import java.util.List;

import com.example.demo.domain.response.product.ResCreateProductDTO.ProductCategory;
import com.example.demo.domain.response.product.ResCreateProductDTO.ProductColor;
import com.example.demo.domain.response.product.ResCreateProductDTO.ProductMaterial;
import com.example.demo.domain.response.product.ResCreateProductDTO.ProductShoe;
import com.example.demo.domain.response.product.ResCreateProductDTO.ProductStyle;
import com.example.demo.util.constant.GenderEnum;

public class ResGetProductDTO {
    private long id;
    private ProductShoe shoe;
    private GenderEnum gender;
    private ProductColor color;
    private ProductMaterial material;
    private ProductStyle style;
    private ProductCategory category;
    private Instant updatedAt;
    private Instant createdAt;
    private String createdBy;
    private String updatedBy;
    private String url;
    private List<ProductImage> images;
    private List<ProductInventory> inventories;

    public static class ProductInventory {
        private long id;
        private int quantity;
        private int size;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public ProductInventory(long id, int quantity, int size) {
            this.id = id;
            this.quantity = quantity;
            this.size = size;
        }

    }

    public static class ProductImage {
        private String url;

        public ProductImage(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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

    public List<ProductInventory> getInventories() {
        return inventories;
    }

    public void setInventories(List<ProductInventory> inventories) {
        this.inventories = inventories;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

}
