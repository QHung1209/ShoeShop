package com.example.demo.service.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.Admin.ColorAdminRepository;
import com.example.demo.entity.Colors;

import com.example.demo.service.Admin.imp.ColorsAdminServiceImp;

@Service
public class ColorAdminService implements ColorsAdminServiceImp{
    @Autowired
    ColorAdminRepository colorsAdminRepository;

    @Override
    public boolean insertColors(String color_name) {
        boolean isSuccess = false;
        try {

            Colors colors = new Colors();
            colors.setColor_name(color_name);
            colorsAdminRepository.save(colors);
            isSuccess = true;

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error insert shoes" + e.getMessage());
        }

        return isSuccess;
    }
}
