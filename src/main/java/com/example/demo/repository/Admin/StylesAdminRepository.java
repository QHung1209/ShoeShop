package com.example.demo.repository.Admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.demo.repository.Admin.imp.StylesAdminRepositoryImp;
import com.example.demo.entity.Styles;

@Repository
public class StylesAdminRepository implements StylesAdminRepositoryImp {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Tìm kiếm theo style_name
    @Override
    public Styles findByStyleName(String style_name) {
        String sql = "SELECT * FROM styles WHERE style_name = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Styles styles = new Styles();
            styles.setStyle_id(rs.getInt("style_id"));
            styles.setStyle_name(rs.getString("style_name"));
            // Set các trường khác nếu cần
            return styles;
        }, style_name);
    }

    // Lưu style_name vào database
    @Override
    public Styles saveStyle(Styles styles) {
        String sql = "INSERT INTO styles (style_id, style_name) VALUES (?, ?)";
        jdbcTemplate.update(sql, styles.getStyle_id(), styles.getStyle_name());
        return styles;
    }

    // Lấy tất cả data trong bảng styles
    @Override
    public List<Styles> findAll() {
        String sql = "SELECT * FROM styles";
        List<Styles> styles = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Styles style = new Styles();
            style.setStyle_id(rs.getInt("style_id"));
            style.setStyle_name(rs.getString("style_name"));
            // Các trường khác nếu cần
            return style;
        });
        return styles;
    }

    // Xóa style theo style_name
    @Override
    public void delete(Styles style) {
        String sql = "DELETE FROM styles WHERE style_name = ?";
        jdbcTemplate.update(sql, style.getStyle_name());
    }

    // Cập nhật style_name  
    @Override
    public void updateStyleName(String style_name, String newStyleName) {
        String sql = "UPDATE styles SET style_name = ? WHERE style_name = ?";
        jdbcTemplate.update(sql, newStyleName, style_name);
    }

    // Kiểm tra style_name có tồn tại không
    @Override
    public boolean isStyleNameExisted(String style_name) {
        String sql = "SELECT COUNT(*) FROM styles WHERE style_name = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, style_name);
        return count > 0;
    }

}
