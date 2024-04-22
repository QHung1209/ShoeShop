package com.example.demo.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name="Sizes")
public class Sizes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int size_id;
    
    @Column(name="size_name")
    private int size_name;

    @OneToMany(mappedBy = "sizes")
    private Set<Products> listProduct;
    
    public Set<Products> getListProduct() {
        return listProduct;
    }

    public void setListProduct(Set<Products> listProduct) {
        this.listProduct = listProduct;
    }

    public int getSize_id() {
        return size_id;
    }

    public void setSize_id(int size_id) {
        this.size_id = size_id;
    }

    public int getSize_name() {
        return size_name;
    }

    public void setSize_name(int size_name) {
        this.size_name = size_name;
    }

    
}
