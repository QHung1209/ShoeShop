package com.example.demo.repository.Admin;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.entity.Admin.Admins;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.demo.repository.Admin.imp.AdminRepositoryImp;

@Repository
public class AdminRepository implements AdminRepositoryImp {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Admins findByAdminName(String name) {
        String sql = "SELECT * FROM admins WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Admins admins = new Admins();
            admins.setUser_id(rs.getInt("user_id"));
            admins.setUsername(rs.getString("username"));
            admins.setPassword(rs.getString("password"));
            admins.setName(rs.getString("name"));
            admins.setAddress(rs.getString("address"));
            admins.setTelephone(rs.getString("telephone"));

            // Set các trường khác nếu cần
            return admins;
        }, name);
    }

    @Override
    public Admins saveAdmin(Admins admins) {
        String sql = "INSERT INTO admins (username, password, name, address, telephone) VALUES ( ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, admins.getUsername(), admins.getPassword(), admins.getName(),
                admins.getAddress(), admins.getTelephone());
        return admins;
    }

    @Override
    public List<Admins> findAll() {
        String sql = "SELECT * FROM admins";
        List<Admins> admins = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Admins admin = new Admins();
            admin.setUser_id(rs.getInt("user_id"));
            admin.setUsername(rs.getString("username"));
            admin.setPassword(rs.getString("password"));
            admin.setName(rs.getString("name"));
            admin.setAddress(rs.getString("address"));
            admin.setTelephone(rs.getString("telephone"));

            // Các trường khác nếu cần
            return admin;
        });
        return admins;
    }

    @Override
    public void delete(Admins admin) {
        String sql = "DELETE FROM admins WHERE user_id = ?";
        jdbcTemplate.update(sql, admin.getUser_id());
    }

    @Override
    public void updateAdmin(int userId, String newUserName, String newPassword, String newName, String newAddress, String newTelephone) {
        String sql = "UPDATE admins SET username = ?, password = ?, name = ?, address = ?, telephone = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, newUserName, newPassword, newName, newAddress, newTelephone, userId);
    }

    @Override
    public boolean isNameExisted(String name) {
        String sql = "SELECT COUNT(*) FROM admins WHERE name = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, name);
        return count > 0;
    }

    @Override
    public List<Admins> findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM admins WHERE username = ? AND password = ?";
        List<Admins> admins = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Admins admin = new Admins();
            admin.setUser_id(rs.getInt("user_id"));
            admin.setUsername(rs.getString("username"));
            admin.setPassword(rs.getString("password"));
            admin.setName(rs.getString("name"));
            admin.setAddress(rs.getString("address"));
            admin.setTelephone(rs.getString("telephone"));

            // Các trường khác nếu cần
            return admin;
        }, username, password);
        return admins;
    }
}
