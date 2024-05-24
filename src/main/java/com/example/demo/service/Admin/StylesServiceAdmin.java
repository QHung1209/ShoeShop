package com.example.demo.service.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Styles;
import com.example.demo.repository.Admin.StylesAdminRepository;
import com.example.demo.service.Admin.imp.StylesAdminServiceImp;
import com.example.demo.dto.Admin.StylesDTO;
import java.util.ArrayList;
import java.util.List;

@Service
public class StylesServiceAdmin implements StylesAdminServiceImp {

    @Autowired
    private StylesAdminRepository stylesAdminRepository;

    @Override
    public boolean insertStyles(String style_name) {
        boolean isSuccess = false;
        try {
            Styles styles = new Styles();
            styles.setStyle_name(style_name);
            stylesAdminRepository.saveStyle(styles);
            isSuccess = true;
        } catch (Exception e) {
            System.out.println("Error insert styles" + e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public List<StylesDTO> getStyles() {
        // TODO Auto-generated method stub
        List<StylesDTO> listStylesDTO = new ArrayList<>();

        List<Styles> listData = stylesAdminRepository.findAll();

        for (Styles data : listData) {
            StylesDTO StylesDTO = new StylesDTO();
            StylesDTO.setStyle_name(data.getStyle_name());
            listStylesDTO.add(StylesDTO);
        }
        return listStylesDTO;
    }

    @Override
    public ResponseEntity<Void> deleteStyleByName(String style_name) {
        Styles styles = stylesAdminRepository.findByStyleName(style_name);
        if (styles != null) {
            stylesAdminRepository.delete(styles);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Void> updateStyleName(String style_name, String newStyleName) {
        Styles styles = stylesAdminRepository.findByStyleName(style_name);
        if (styles != null) {
            stylesAdminRepository.updateStyleName(style_name, newStyleName);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public boolean checkStyleExists(String style_name) {
        return stylesAdminRepository.isStyleNameExisted(style_name);
    }
}
