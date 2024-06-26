package com.example.demo.repository.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Categories;
import com.example.demo.repository.Admin.imp.CategoriesAdminRepositoryImp;
import java.util.List;

@Repository
public class CategoriesAdminRepository implements CategoriesAdminRepositoryImp {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Categories findByCategoryName(String category_name) {
        String sql = "SELECT * FROM categories WHERE category_name = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Categories categories = new Categories();
            categories.setCategory_id(rs.getInt("category_id"));
            categories.setCategory_name(rs.getString("category_name"));
            // Set các trường khác nếu cần
            return categories;
        }, category_name);
    }

    @Override
    public Categories saveCategory(Categories categories) {
        String sql = "INSERT INTO categories (category_id, category_name) VALUES (?, ?)";
        jdbcTemplate.update(sql, categories.getCategory_id(), categories.getCategory_name());
        return categories;
    }

    @Override
    public List<Categories> findAll() {
        String sql = "SELECT * FROM categories";
        List<Categories> categories = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Categories category = new Categories();
            category.setCategory_id(rs.getInt("category_id"));
            category.setCategory_name(rs.getString("category_name"));
            // Các trường khác nếu cần
            return category;
        });
        return categories;
    }

    @Override
    public void delete(Categories categories) {
        String sql = "DELETE FROM categories WHERE category_name = ?";
        jdbcTemplate.update(sql, categories.getCategory_name());
    }

    @Override
    public void updateCategoryName(String category_name, String newCategoryName) {
        String sql = "UPDATE categories SET category_name = ? WHERE category_name = ?";
        jdbcTemplate.update(sql, newCategoryName, category_name);
    }

    @Override
    public boolean isCategoryNameExisted(String category_name) {
        String sql = "SELECT COUNT(*) FROM categories WHERE category_name = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, category_name);
        return count > 0;
    }
}
