package com.example.demo.repository.Admin.imp;

import java.util.List;
import com.example.demo.entity.Categories;

public interface CategoriesAdminRepositoryImp {
    List<Categories> findAll();

    Categories findByCategoryName(String category_name);

    Categories saveCategory(Categories categories);

    void delete(Categories categories);

    void updateCategoryName(String category_name, String newCategoryName);

    boolean isCategoryNameExisted(String category_name);
}
