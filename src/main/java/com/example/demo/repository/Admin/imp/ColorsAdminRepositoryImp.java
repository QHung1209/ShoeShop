package com.example.demo.repository.Admin.imp;

import java.util.List;

import com.example.demo.entity.Colors;

public interface ColorsAdminRepositoryImp {
    List<Colors> findAll();

    Colors findByColorName(String color_name);

    Colors findByColorCode(String color_code);

    Colors saveColor(Colors color);

    void updateColorName(String color_name, String newColorName);

    void updateColorCode(String color_code, String newColorCode);

    boolean isColorCodeExisted(String color_code);

    boolean isColorNameExisted(String color_name);
}
