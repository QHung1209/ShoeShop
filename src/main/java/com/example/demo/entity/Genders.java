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

    @Column(name="material_name")
    private String gender_name;

    @OneToMany(mappedBy = "genders")
    private Set<Products> listInventory;

}
