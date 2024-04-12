package com.example.demo.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "Shoes")
public class Shoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shoe_id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private int price;

    @OneToMany(mappedBy = "shoes")
    private Set<Inventories> listInventory;

    public Set<Inventories> getListInventory() {
        return listInventory;
    }

    public void setListInventory(Set<Inventories> listInventory) {
        this.listInventory = listInventory;
    }

    public int getShoe_id() {
        return shoe_id;
    }

    public void setShoe_id(int shoe_id) {
        this.shoe_id = shoe_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
