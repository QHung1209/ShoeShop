package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="Inventories")
public class Inventories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inventory_id;

    @Column(name="quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "shoe_id")
    private Shoes shoes;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Colors colors;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private Sizes sizes;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories categories;

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

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

    public Sizes getSizes() {
        return sizes;
    }

    public void setSizes(Sizes sizes) {
        this.sizes = sizes;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
