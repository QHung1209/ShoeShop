package com.example.demo.repository.Admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.demo.repository.Admin.imp.StylesAdminRepositoryImp;
import com.example.demo.entity.Styles;

@Repository
public class StylesAdminRepository implements StylesAdminRepositoryImp {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Styles findByStyleName(String style_name) {
        String sql = "SELECT * FROM styles WHERE style_name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { style_name }, (rs, rowNum) -> {
            Styles styles = new Styles();
            styles.setStyle_id(rs.getInt("style_id"));
            styles.setStyle_name(rs.getString("style_name"));
            // Set các trường khác nếu cần
            return styles;
        });
    }

    @Override
    public Styles saveStyle(Styles styles) {
        String sql = "INSERT INTO styles (style_id, style_name) VALUES (?, ?)";
        jdbcTemplate.update(sql, styles.getStyle_id(), styles.getStyle_name());
        return styles;
    }

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

    @Override
    public void delete(Styles style) {
        String sql = "DELETE FROM styles WHERE style_name = ?";
        jdbcTemplate.update(sql, style.getStyle_name());
    }
}
