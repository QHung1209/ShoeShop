package com.example.demo.repository.Admin.imp;

import java.util.List;

import com.example.demo.entity.Shoes;

public interface ShoesAdminRepositoryImp {
    List<Shoes> findAll();

    Shoes findByShoesName(String name);

    Shoes findByShoesPrice(int price);

    Shoes saveShoes(Shoes shoes);

    void updateShoesName(String name, String newName);

    void updateShoesPrice(String name, int newPrice);

    boolean isShoesNameExisted(String name);
}
