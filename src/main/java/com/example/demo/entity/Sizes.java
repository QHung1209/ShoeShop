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
    private String size_name;

    @OneToMany(mappedBy = "sizes")
    private Set<Inventories> listInventory;
    
    public Set<Inventories> getListInventory() {
        return listInventory;
    }

    public void setListInventory(Set<Inventories> listInventory) {
        this.listInventory = listInventory;
    }

    public int getSize_id() {
        return size_id;
    }

    public void setSize_id(int size_id) {
        this.size_id = size_id;
    }

    public String getSize_name() {
        return size_name;
    }

    public void setSize_name(String size_name) {
        this.size_name = size_name;
    }

    
}