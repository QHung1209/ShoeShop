package com.example.demo.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Materials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int material_id;

    @Column(name="material_name")
    private String material_name;

    @OneToMany(mappedBy = "materials")
    private Set<Products> listInventory;

    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public Set<Products> getListInventory() {
        return listInventory;
    }

    public void setListInventory(Set<Products> listInventory) {
        this.listInventory = listInventory;
    }
}
