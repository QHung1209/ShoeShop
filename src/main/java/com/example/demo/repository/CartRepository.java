package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
//     @Query("SELECT c FROM Carts c JOIN c.products p JOIN c.sizes s JOIN c.users u WHERE (:user_id = u.user_id) AND (:product_id = p.product_id) AND (:size_id = s.size_id)")
//     public Cart findCart(@Param("user_id") int user_id,
//             @Param("product_id") int product_id,
//             @Param("size_id") int size_id);

//     @Query("SELECT c FROM Carts c WHERE c.users.user_id = :user_id AND c.products.product_id = :product_id AND c.sizes.size_id = :size_id")
//     Cart findCartByUserIdAndProductId(@Param("user_id") int user_id, @Param("product_id") int product_id,
//             @Param("size_id") int size_id);

//     @Query("SELECT c FROM Carts c WHERE c.users.user_id = :user_id")
//     List<Cart> findAllCartById(@Param("user_id") int user_id);

//     @Modifying
//     @Transactional
//     @Query("UPDATE Carts c SET c.quantity = :quantity , c.sizes.size_id = :size_id WHERE c.cart_id = :cart_id ")
//     void updateCartBy(@Param("cart_id") int cart_id, @Param("size_id") int size_id, @Param("quantity") int quantity);

//     @Modifying
//     @Transactional
//     @Query("DELETE FROM Carts c WHERE c.cart_id = :cart_id")
//     void deleteAllCartByCartId(@Param("cart_id") int cart_id);

//     @Modifying
//     @Transactional
//     @Query("DELETE FROM Carts c WHERE c.users.user_id = :user_id")
//     void deleteAllCart(@Param("user_id") int user_id);

    List<Cart> findByUserId(long id);
    Cart findByUserIdAndProductIdAndSizeId(long userId, long productId, long sizeId);
}
