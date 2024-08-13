package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.example.demo.domain.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    // @Query("SELECT MAX(o.order_id) FROM Orders o WHERE o.users.user_id = :user_id")
    // int findMaxOrderId(int user_id);

    // @Query("SELECT o FROM Orders o WHERE o.users.user_id = :user_id")
    // List<Order> findAllOrder(int user_id);

    // @Query("SELECT o FROM Orders o WHERE o.order_status = 0")
    // List<Order> getUnconfirmedOrders();

    // @Modifying
    // @Transactional
    // @Query("DELETE FROM Orders o WHERE o.order_id = :order_id")
    // void DeleteOrder(int order_id);

    Page<Order> findByUserId(long id, Pageable pageable);
   
    Optional<Order> findTopByUserIdOrderByCreatedAtDesc(Long userId);

}
