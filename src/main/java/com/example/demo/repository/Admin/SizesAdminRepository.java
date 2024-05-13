package com.example.demo.repository.Admin;

import org.springframework.stereotype.Repository;
import com.example.demo.entity.Sizes;
import com.example.demo.repository.Admin.imp.SizesAdminRepositoryImp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class SizesAdminRepository implements SizesAdminRepositoryImp {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Sizes findBySizeName(int size_name) {
        String sql = "SELECT * FROM sizes WHERE size_name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { size_name }, (rs, rowNum) -> {
            Sizes sizes = new Sizes();
            sizes.setSize_id(rs.getInt("size_id"));
            sizes.setSize_name(rs.getInt("size_name"));
            // Set các trường khác nếu cần
            return sizes;
        });
    }

    @Override
    public Sizes saveSize(Sizes sizes) {
        String sql = "INSERT INTO sizes (size_id, size_name) VALUES (?, ?)";
        jdbcTemplate.update(sql, sizes.getSize_id(), sizes.getSize_name());
        return sizes;
    }

    @Override
    public List<Sizes> findAll() {
        String sql = "SELECT * FROM sizes";
        List<Sizes> sizes = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Sizes size = new Sizes();
            size.setSize_id(rs.getInt("size_id"));
            size.setSize_name(rs.getInt("size_name"));
            // Các trường khác nếu cần
            return size;
        });
        return sizes;
    }


    @Override
    public void delete(Sizes size) {
        String sql = "DELETE FROM sizes WHERE size_name = ?";
        jdbcTemplate.update(sql, size.getSize_name());
    }
    
}