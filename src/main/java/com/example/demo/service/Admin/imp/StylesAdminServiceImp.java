package com.example.demo.service.Admin.imp;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.Admin.StylesDTO;

public interface StylesAdminServiceImp {
    boolean insertStyles(String style_name);

    List<StylesDTO> getStyles();

    ResponseEntity<Void> deleteStyleByName(String style_name);

    ResponseEntity<Void> updateStyleName(String style_name, String newStyleName);

    boolean checkStyleExists(String style_name);

}
