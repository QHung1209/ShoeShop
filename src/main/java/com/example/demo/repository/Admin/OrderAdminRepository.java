package com.example.demo.repository.Admin;

import java.util.List;

import com.example.demo.entity.Orders;
import com.example.demo.repository.Admin.imp.OrderAdminRepositoryImp;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class OrderAdminRepository implements OrderAdminRepositoryImp {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Orders> findAll() {
        String sql = "SELECT * FROM Orders";
        List<Orders> list_orders = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Orders orders = new Orders();
            orders.setName(rs.getString("name"));
            orders.setAddress(rs.getString("address"));
            orders.setTelephone(rs.getString("telephone"));
            orders.setTotal_amount(rs.getDouble("total_amount"));
            orders.setDate_order(rs.getTimestamp("date_order"));
            orders.setOrder_status(rs.getInt("order_status"));
            // Các trường khác nếu cần
            return orders;
        });
        return list_orders;
    }

}
