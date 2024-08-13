package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>, JpaSpecificationExecutor<OrderDetail>{

    // @Query("SELECT ord FROM Order_detail ord WHERE ord.orders.order_id =:order_id")
    // List<OrderDetail> findAllOrderDetail(int order_id);

    // @Modifying
    // @Transactional
    // @Query("DELETE FROM Order_detail ord WHERE ord.orders.order_id =:order_id")
    // void DeleteAllOrderDetail(int order_id);
    List<OrderDetail> findByOrderId(long id);
}
