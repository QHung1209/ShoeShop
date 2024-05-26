package com.example.demo.repository.Admin;

import java.util.List;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import com.example.demo.dto.Admin.OrderDetailAdminDTO;
import com.example.demo.entity.Order_detail;
import com.example.demo.entity.Orders;
import com.example.demo.repository.Admin.imp.OrderAdminRepositoryImp;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class OrderAdminRepository implements OrderAdminRepositoryImp {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Orders> findAll() {
        String sql = "SELECT * FROM Orders";
        List<Orders> list_orders = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Orders orders = new Orders();
            orders.setOrder_id(rs.getInt("order_id"));
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

    private EntityManager entityManager;

    public OrderAdminRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void updateOrderStatusAndInventory(int orderId) {
        try {
            // Cập nhật trạng thái của đơn hàng
            Query updateOrderStatusQuery = entityManager.createQuery(
                    "UPDATE Orders o SET o.order_status = 1 WHERE o.order_id = :orderId");
            updateOrderStatusQuery.setParameter("orderId", orderId);
            updateOrderStatusQuery.executeUpdate();

            // Truy vấn thông tin chi tiết đơn hàng
            Query getOrderDetailQuery = entityManager.createQuery(
                    "SELECT od FROM Order_detail od WHERE od.orders.order_id = :orderId");
            getOrderDetailQuery.setParameter("orderId", orderId);
            List<Order_detail> orderDetails = getOrderDetailQuery.getResultList();

            // Cập nhật số lượng sản phẩm trong kho
            for (Order_detail detail : orderDetails) {
                int productId = detail.getProducts().getProduct_id();
                int sizeId = detail.getSizes().getSize_id();
                int quantityOrdered = detail.getQuantity();

                Query updateInventoryQuery = entityManager.createQuery(
                        "UPDATE Inventory i SET i.quantity = i.quantity - :quantity " +
                                "WHERE i.products.product_id = :productId AND i.sizes.size_id = :sizeId");
                updateInventoryQuery.setParameter("quantity", quantityOrdered);
                updateInventoryQuery.setParameter("productId", productId);
                updateInventoryQuery.setParameter("sizeId", sizeId);
                updateInventoryQuery.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating order status and inventory", e);
        }
    }

    public List<OrderDetailAdminDTO> getOrderDetailsByOrderId(int orderId) {
        String jpql = "SELECT new com.example.demo.dto.Admin.OrderDetailAdminDTO(od.order_detail_id, s.name, sz.size_name, od.quantity, od.price) "
                +
                "FROM Order_detail od " +
                "JOIN od.products p " +
                "JOIN p.shoes s " +
                "JOIN od.sizes sz " +
                "WHERE od.orders.order_id = :orderId";
        TypedQuery<OrderDetailAdminDTO> query = entityManager.createQuery(jpql, OrderDetailAdminDTO.class);
        query.setParameter("orderId", orderId);
        return query.getResultList();
    }

}
