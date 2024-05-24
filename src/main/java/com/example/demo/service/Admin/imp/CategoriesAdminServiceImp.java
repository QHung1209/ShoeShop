package com.example.demo.service.Admin.imp;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.Admin.CategoriesDTO;

public interface CategoriesAdminServiceImp {
    // Thêm mới một category
    boolean insertCategories(String category_name);

    // Lấy danh sách các category
    List<CategoriesDTO> getCategories();

    // Xóa một category theo tên
    ResponseEntity<Void> deleteCategoryByName(String category_name);

    // Cập nhật tên category
    ResponseEntity<Void> updateCategoryName(String category_name, String newCategoryName);

    // Kiểm tra category có tồn tại không
    boolean checkCategoryExists(String category_name);

}
