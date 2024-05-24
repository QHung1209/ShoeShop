package com.example.demo.repository.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.demo.repository.Admin.imp.ColorsAdminRepositoryImp;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

import com.example.demo.entity.Colors;

@Repository
public class ColorsAdminRepository implements ColorsAdminRepositoryImp {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Tìm kiếm theo color_name
    @Override
    public Colors findByColorName(String color_name) {
        String sql = "SELECT * FROM colors WHERE color_name = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Colors colors = new Colors();
            colors.setColor_id(rs.getInt("color_id"));
            colors.setColor_name(rs.getString("color_name"));
            // Set các trường khác nếu cần
            return colors;
        }, color_name);
    }

    // Tìm kiếm theo color_code
    @Override
    public Colors findByColorCode(String color_code) {
        String sql = "SELECT * FROM colors WHERE color_code = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Colors colors = new Colors();
            colors.setColor_id(rs.getInt("color_id"));
            colors.setColor_code(rs.getString("color_code"));
            // Set các trường khác nếu cần
            return colors;
        }, color_code);
    }

    // Lưu color_name vào database
    @Override
    public Colors saveColor(Colors colors) {
        String sql = "INSERT INTO colors (color_id, color_code, color_name) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, colors.getColor_id(), colors.getColor_code(), colors.getColor_name());
        return colors;
    }

    // Lấy tất cả data trong bảng colors
    @Override
    public List<Colors> findAll() {
        String sql = "SELECT * FROM colors";
        List<Colors> colors = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Colors color = new Colors();
            color.setColor_id(rs.getInt("color_id"));
            color.setColor_code(rs.getString("color_code"));
            color.setColor_name(rs.getString("color_name"));
            // Các trường khác nếu cần
            return color;
        });
        return colors;
    }

    // Cập nhật color_name
    @Override
    public void updateColorName(String color_name, String newColorName) {
        String sql = "UPDATE colors SET color_name = ? WHERE color_name = ?";
        jdbcTemplate.update(sql, newColorName, color_name);
    }

    // Cập nhật color_code
    @Override
    public void updateColorCode(String color_code, String newColorCode) {
        String sql = "UPDATE colors SET color_code = ? WHERE color_code = ?";
        jdbcTemplate.update(sql, newColorCode, color_code);
    }

    // Kiểm tra color_code có tồn tại không
    @Override
    public boolean isColorCodeExisted(String color_code) {
        String sql = "SELECT COUNT(*) FROM colors WHERE color_code = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, color_code);
        return count > 0;
    }

    // Kiểm tra color_name có tồn tại không
    @Override
    public boolean isColorNameExisted(String color_name) {
        String sql = "SELECT COUNT(*) FROM colors WHERE color_name = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, color_name);
        return count > 0;
    }

}
