package com.example.demo.service.Admin.imp;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.Admin.SizesDTO;

public interface SizesAdminServiceImp {
    boolean insertSizes(int size_name);

    List<SizesDTO> getSizes();

    ResponseEntity<Void> deleteSizeByName(int size_name);

    ResponseEntity<Void> updateSizeName(int size_name, int newSizeName);

    boolean checkSizeExists(int size_name);

    List<Integer> getAllSizeName();
}
