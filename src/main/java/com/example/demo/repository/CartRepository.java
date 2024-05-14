package com.example.demo.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Carts;

@Repository
public interface CartRepository extends JpaRepository<Carts, Integer> {
    @Query("SELECT c FROM Carts c JOIN c.products p JOIN c.sizes s JOIN c.users u WHERE (:user_id = u.user_id) AND (:product_id = p.product_id ) AND (:size_id = s.size_id)")
    public Page<Carts> findCart(@Param("user_id") int user_id,
            @Param("product_id") int product_id,
            @Param("size_id") int size_id,
            Pageable pageable);

    @Query("SELECT c FROM Carts c WHERE c.users.user_id = :user_id AND c.products.product_id = :product_id AND c.sizes.size_id = :size_id")
    Carts findCartByUserIdAndProductId(@Param("user_id") int user_id, @Param("product_id") int product_id, @Param("size_id") int size_id );

}
