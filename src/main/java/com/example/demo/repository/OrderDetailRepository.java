package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Order_detail;

import jakarta.transaction.Transactional;

@Repository
public interface OrderDetailRepository extends JpaRepository<Order_detail, Integer>{

    @Query("SELECT ord FROM Order_detail ord WHERE ord.orders.order_id =:order_id")
    List<Order_detail> findAllOrderDetail(int order_id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Order_detail ord WHERE ord.orders.order_id =:order_id")
    void DeleteAllOrderDetail(int order_id);
}
