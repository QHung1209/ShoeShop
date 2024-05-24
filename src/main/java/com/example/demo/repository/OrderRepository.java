package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
    @Query("SELECT MAX(o.order_id) FROM Orders o WHERE o.users.user_id = :user_id")
    int findMaxOrderId(int user_id);

    @Query("SELECT o FROM Orders o WHERE o.users.user_id = :user_id")
    List<Orders> findAllOrder(int user_id);
}