package com.example.demo.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Genders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gender_id;

    @Column(name="gender_name")
    private String gender_name;

    @OneToMany(mappedBy = "genders")
    private Set<Products> listProduct;

    public int getGender_id() {
        return gender_id;
    }

    public void setGender_id(int gender_id) {
        this.gender_id = gender_id;
    }

    public String getGender_name() {
        return gender_name;
    }

    public void setGender_name(String gender_name) {
        this.gender_name = gender_name;
    }

    public Set<Products> getListProduct() {
        return listProduct;
    }

    public void setListProduct(Set<Products> listProduct) {
        this.listProduct = listProduct;
    }

}
