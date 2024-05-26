package com.example.demo.service.Admin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.repository.Admin.ColorsAdminRepository;
import com.example.demo.dto.Admin.ColorsDTO;
import com.example.demo.entity.Colors;
import com.example.demo.service.Admin.imp.ColorsAdminServiceImp;

@Service
public class ColorsAdminService implements ColorsAdminServiceImp {
    @Autowired
    ColorsAdminRepository colorsAdminRepository;

    @Override
    public boolean insertColors(String color_code, String color_name) {
        boolean isSuccess = false;
        try {

            Colors colors = new Colors();
            colors.setColor_name(color_name);
            colors.setColor_code(color_code);
            colorsAdminRepository.saveColor(colors);
            isSuccess = true;

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error insert shoes" + e.getMessage());
        }

        return isSuccess;
    }

    // Lấy dữ liệu từ colors table
    @Override
    public List<ColorsDTO> getColors() {
        // TODO Auto-generated method stub
        List<ColorsDTO> listColorsDTO = new ArrayList<>();

        List<Colors> listData = colorsAdminRepository.findAll();

        for (Colors data : listData) {
            ColorsDTO colorsDTO = new ColorsDTO();
            colorsDTO.setColor_code(data.getColor_code());
            colorsDTO.setColor_name(data.getColor_name());
            listColorsDTO.add(colorsDTO);
        }
        return listColorsDTO;
    }

    // Update color name
    @Override
    public ResponseEntity<Void> updateColorName(String color_name, String newColorName) {
        Colors colors = colorsAdminRepository.findByColorName(color_name);
        if (colors != null) {
            colorsAdminRepository.updateColorName(color_name, newColorName);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update color code
    @Override
    public ResponseEntity<Void> updateColorCode(String color_code, String newColorCode) {
        Colors colors = colorsAdminRepository.findByColorCode(color_code);
        if (colors != null) {
            colorsAdminRepository.updateColorCode(color_code, newColorCode);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public boolean checkColorNameExists(String color_name) {
        // TODO Auto-generated method stub
        return colorsAdminRepository.isColorNameExisted(color_name);
    }

    @Override
    public boolean checkColorCodeExists(String color_code) {
        // TODO Auto-generated method stub
        return colorsAdminRepository.isColorCodeExisted(color_code);
    }

    @Override
    public List<String> getAllColorName() {
        List<Colors> colorsList = colorsAdminRepository.findAll();
        return colorsList.stream()
                .map(Colors::getColor_name)
                .collect(Collectors.toList());
    }
}
