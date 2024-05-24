package com.example.demo.repository.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Materials;
import com.example.demo.repository.Admin.imp.MaterialAdminRepositoryImp;
import java.util.List;

@Repository
public class MaterialsAdminRepository implements MaterialAdminRepositoryImp {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Materials findByMaterialName(String material_name) {
        String sql = "SELECT * FROM materials WHERE material_name = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Materials materials = new Materials();
            materials.setMaterial_id(rs.getInt("material_id"));
            materials.setMaterial_name(rs.getString("material_name"));
            // Set các trường khác nếu cần
            return materials;
        }, material_name);
    }

    @Override
    public Materials saveMaterial(Materials materials) {
        String sql = "INSERT INTO materials (material_id, material_name) VALUES (?, ?)";
        jdbcTemplate.update(sql, materials.getMaterial_id(), materials.getMaterial_name());
        return materials;
    }

    @Override
    public List<Materials> findAll() {
        String sql = "SELECT * FROM materials";
        List<Materials> materials = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Materials material = new Materials();
            material.setMaterial_id(rs.getInt("material_id"));
            material.setMaterial_name(rs.getString("material_name"));
            // Các trường khác nếu cần
            return material;
        });
        return materials;
    }

    @Override
    public void delete(Materials material) {
        String sql = "DELETE FROM materials WHERE material_name = ?";
        jdbcTemplate.update(sql, material.getMaterial_name());
    }

    @Override
    public void updateMaterialName(String material_name, String newMaterialName) {
        String sql = "UPDATE materials SET material_name = ? WHERE material_name = ?";
        jdbcTemplate.update(sql, newMaterialName, material_name);
    }

    @Override
    public boolean isMaterialNameExisted(String material_name) {
        String sql = "SELECT COUNT(*) FROM materials WHERE material_name = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, material_name);
        return count > 0;
    }
}
