package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name="Colors")
public class Colors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int color_id;

    @Column(name="color_name")
    private String color_name;

    @Column(name="color_code")
    private String color_code;
    
    @OneToMany(mappedBy = "colors")
    private List<Products> listProduct;

    public int getColor_id() {
        return color_id;
    }


    public void setColor_id(int color_id) {
        this.color_id = color_id;
    }

    public String getColor_name() {
        return color_name;
    }

    public void setColor_name(String color_name) {
        this.color_name = color_name;
    }

    public List<Products> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Products> listProduct) {
        this.listProduct = listProduct;
    }


    public String getColor_code() {
        return color_code;
    }


    public void setColor_code(String color_code) {
        this.color_code = color_code;
    }
}
