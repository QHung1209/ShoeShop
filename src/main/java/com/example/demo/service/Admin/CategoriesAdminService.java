package com.example.demo.service.Admin;

import com.example.demo.dto.Admin.CategoriesDTO;
import com.example.demo.entity.Categories;
import com.example.demo.repository.Admin.CategoriesAdminRepository;
import com.example.demo.service.Admin.imp.CategoriesAdminServiceImp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoriesAdminService implements CategoriesAdminServiceImp {

    @Autowired
    private CategoriesAdminRepository categoriesAdminRepository;

    @Override
    // Thêm mới một category
    public boolean insertCategories(String category_name) {
        boolean isSuccess = false;
        try {
            Categories categories = new Categories();
            categories.setCategory_name(category_name);
            categoriesAdminRepository.saveCategory(categories);
            isSuccess = true;
        } catch (Exception e) {
            System.out.println("Error insert categories" + e.getMessage());
        }
        return isSuccess;
    }

    @Override
    // Lấy danh sách các category
    public List<CategoriesDTO> getCategories() {
        // TODO Auto-generated method stub
        List<CategoriesDTO> listCategoriesDTO = new ArrayList<>();

        List<Categories> listData = categoriesAdminRepository.findAll();

        for (Categories data : listData) {
            CategoriesDTO CategoriesDTO = new CategoriesDTO();
            CategoriesDTO.setCategory_name(data.getCategory_name());
            listCategoriesDTO.add(CategoriesDTO);
        }
        return listCategoriesDTO;
    }

    @Override
    // Xóa một category theo tên
    public ResponseEntity<Void> deleteCategoryByName(String category_name) {
        Categories categories = categoriesAdminRepository.findByCategoryName(category_name);
        if (categories != null) {
            categoriesAdminRepository.delete(categories);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    // Cập nhật tên category
    public ResponseEntity<Void> updateCategoryName(String category_name, String newCategoryName) {
        Categories categories = categoriesAdminRepository.findByCategoryName(category_name);
        if (categories != null) {
            categoriesAdminRepository.updateCategoryName(category_name, newCategoryName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    // Kiểm tra category có tồn tại không
    public boolean checkCategoryExists(String category_name) {
        return categoriesAdminRepository.isCategoryNameExisted(category_name);
    }
}
