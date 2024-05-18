package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Order_detail;

@Repository
public interface OrderDetailRepository extends JpaRepository<Order_detail, Integer>{

}
