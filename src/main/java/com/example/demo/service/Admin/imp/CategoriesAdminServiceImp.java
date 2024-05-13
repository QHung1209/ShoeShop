package com.example.demo.service.Admin.imp;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.Admin.CategoriesDTO;
public interface CategoriesAdminServiceImp {
    boolean insertCategories(String category_name);
    List<CategoriesDTO> getCategories();
    ResponseEntity<Void> deleteCategoryByName(String category_name);
}
