package com.example.demo.entity;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name="Products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;

    @OneToMany(mappedBy = "products")
    private List<Inventory> listInventories;
    
    @OneToMany(mappedBy = "products")
    private List<Order_detail> listOrder_details;
    public List<Inventory> getListInventories() {
        return listInventories;
    }

    public void setListInventories(List<Inventory> lInventories) {
        this.listInventories = lInventories;
    }

    @ManyToOne
    @JoinColumn(name = "shoe_id")
    private Shoes shoes;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Colors colors;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories categories;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Genders genders;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Materials materials;

    @ManyToOne
    @JoinColumn(name = "style_id")
    private Styles styles;

    @OneToMany(mappedBy = "products")
    private List<Carts> carts;
    
    @Column(name="image_url")
    private String image_url;

    @Column(name="discount")
    private int discount;

    public Shoes getShoes() {
        return shoes;
    }

    public void setShoes(Shoes shoes) {
        this.shoes = shoes;
    }

    public Colors getColors() {
        return colors;
    }

    public void setColors(Colors colors) {
        this.colors = colors;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public String getImage() {
        return image_url;
    }

    public void setImage(String image) {
        this.image_url = image;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public Genders getGenders() {
        return genders;
    }

    public void setGenders(Genders genders) {
        this.genders = genders;
    }

    public Materials getMaterials() {
        return materials;
    }

    public void setMaterials(Materials materials) {
        this.materials = materials;
    }

    public Styles getStyles() {
        return styles;
    }

    public void setStyle(Styles styles) {
        this.styles = styles;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setStyles(Styles styles) {
        this.styles = styles;
    }

    public List<Carts> getCarts() {
        return carts;
    }

    public void setCarts(List<Carts> carts) {
        this.carts = carts;
    }

    public List<Order_detail> getListOrder_details() {
        return listOrder_details;
    }

    public void setListOrder_details(List<Order_detail> listOrder_details) {
        this.listOrder_details = listOrder_details;
    }

    

}
