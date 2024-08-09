package com.example.demo.domain.response.product;

import java.time.Instant;

import com.example.demo.util.constant.GenderEnum;

public class ResCreateProductDTO {
    private long id;
    private ProductShoe shoe;
    private GenderEnum gender;
    private ProductColor color;
    private ProductMaterial material;
    private ProductStyle style;
    private ProductCategory category;
    private Instant createdAt;
    private String createdBy;
    private String url;

    public static class ProductShoe {
        private String name;
        private double price;

        public ProductShoe(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }

    public static class ProductColor {
        private String name;
        private String code;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public ProductColor(String name, String code) {
            this.name = name;
            this.code = code;
        }

    }

    public static class ProductMaterial {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ProductMaterial(String name) {
            this.name = name;
        }

    }

    public static class ProductStyle {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ProductStyle(String name) {
            this.name = name;
        }

    }

    public static class ProductCategory {
        private String name;

        public ProductCategory(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

}
