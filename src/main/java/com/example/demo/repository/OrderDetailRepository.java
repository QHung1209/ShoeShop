package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Order_detail;

@Repository
public interface OrderDetailRepository extends JpaRepository<Order_detail, Integer>{

    @Query("SELECT ord FROM Order_detail ord WHERE ord.orders.order_id =:order_id")
    List<Order_detail> findAllOrderDetail(int order_id);
}
