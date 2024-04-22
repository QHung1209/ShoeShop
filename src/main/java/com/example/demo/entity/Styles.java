package com.example.demo.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Styles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int style_id;

    @Column(name="Style_name")
    private String style_name;

    @OneToMany(mappedBy = "styles")
    private Set<Products> listProduct;

    public int getStyle_id() {
        return style_id;
    }

    public void Style_id(int type_id) {
        this.style_id = type_id;
    }

    public Set<Products> getListProduct() {
        return listProduct;
    }

    public void setListProduct(Set<Products> listProduct) {
        this.listProduct = listProduct;
    }

    public void setStyle_id(int style_id) {
        this.style_id = style_id;
    }

    public String getStyle_name() {
        return style_name;
    }

    public void setStyle_name(String style_name) {
        this.style_name = style_name;
    }

}
