package com.example.demo.repository.Admin;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.demo.entity.Shoes;
import com.example.demo.repository.Admin.imp.ShoesAdminRepositoryImp;
import java.util.List;

@Repository
public class ShoesAdminRepository implements ShoesAdminRepositoryImp {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Tìm kiếm theo name
    @Override
    public Shoes findByShoesName(String name) {
        String sql = "SELECT * FROM shoes WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Shoes shoes = new Shoes();
            shoes.setName(rs.getString("name"));
            shoes.setPrice(rs.getInt("price"));
            // Set các trường khác nếu cần
            return shoes;
        }, name);
    }

    // Tìm kiếm theo price
    @Override
    public Shoes findByShoesPrice(int price) {
        String sql = "SELECT * FROM shoes WHERE price = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Shoes shoes = new Shoes();
            shoes.setName(rs.getString("name"));
            shoes.setPrice(rs.getInt("price"));
            // Set các trường khác nếu cần
            return shoes;
        }, price);
    }

    // Lưu shoes vào database
    @Override
    public Shoes saveShoes(Shoes shoes) {
        String sql = "INSERT INTO shoes (shoe_id, name, price) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, shoes.getShoe_id(), shoes.getName(), shoes.getPrice());
        return shoes;
    }

    // Lấy tất cả data trong bảng shoes
    @Override
    public List<Shoes> findAll() {
        String sql = "SELECT * FROM Shoes";
        List<Shoes> list_shoes = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Shoes shoes = new Shoes();
            shoes.setShoe_id(rs.getInt("shoe_id"));
            shoes.setName(rs.getString("name"));
            shoes.setPrice(rs.getInt("price"));
            // Các trường khác nếu cần
            return shoes;
        });
        return list_shoes;
    }

    // Cập nhật name
    @Override
    public void updateShoesName(String name, String newName) {
        String sql = "UPDATE shoes SET name = ? WHERE name = ?";
        jdbcTemplate.update(sql, newName, name);
    }

    // Cập nhật price
    @Override
    public void updateShoesPrice(String name, int newPrice) {
        String sql = "UPDATE shoes SET price = ? WHERE name = ?";
        jdbcTemplate.update(sql, newPrice, name);
    }

    // Kiểm tra name có tồn tại không
    @Override
    public boolean isShoesNameExisted(String name) {
        String sql = "SELECT COUNT(*) FROM shoes WHERE name = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, name);
        return count > 0;
    }

}
