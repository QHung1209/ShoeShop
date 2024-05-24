package com.example.demo.service.Admin.imp;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.Admin.ColorsDTO;

public interface ColorsAdminServiceImp {

    boolean insertColors(String color_code, String color_name);

    List<ColorsDTO> getColors();

    ResponseEntity<Void> updateColorName(String color_name, String newColorName);

    ResponseEntity<Void> updateColorCode(String color_code, String newColorCode);

    boolean checkColorNameExists(String color_name);

    boolean checkColorCodeExists(String color_code);

    List<String> getAllColorName();
}
