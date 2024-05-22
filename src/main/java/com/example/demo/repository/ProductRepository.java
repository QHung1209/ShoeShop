package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {

        @Query("SELECT p FROM Products p  WHERE (:style IS NULL OR p.styles.style_name IN :style) AND (:material IS NULL OR p.materials.material_name IN :material) AND (:category IS NULL OR p.categories.category_name IN :category) AND (:gender IS NULL OR p.genders.gender_name IN :gender) AND (:price IS NULL OR p.shoes.price >= 1000000) ")
        public Page<Products> findProductsGT1000(@Param("style") List<String> styles,
                        @Param("material") List<String> material, @Param("category") List<String> categories,
                        @Param("gender") List<String> gender,
                        @Param("price") List<String> price,
                        Pageable pageable);

        @Query("SELECT p FROM Products p WHERE (:style IS NULL OR p.styles.style_name IN :style) AND (:material IS NULL OR p.materials.material_name IN :material) AND (:category IS NULL OR p.categories.category_name IN :category) AND (:gender IS NULL OR p.genders.gender_name IN :gender) AND (:price IS NULL OR p.shoes.price BETWEEN 600000 AND 999999) ")
        public Page<Products> findProducts6_9(@Param("style") List<String> styles,
                        @Param("material") List<String> material,
                        @Param("category") List<String> categories, @Param("gender") List<String> gender,
                        @Param("price") List<String> price,
                        Pageable pageable);

        @Query("SELECT p FROM Products p WHERE (:style IS NULL OR p.styles.style_name IN :style) AND (:material IS NULL OR p.materials.material_name IN :material) AND (:category IS NULL OR p.categories.category_name IN :category) AND (:gender IS NULL OR p.genders.gender_name IN :gender) AND (:price IS NULL OR p.shoes.price BETWEEN 300000 AND 599999) ")
        public Page<Products> findProducts3_5(@Param("style") List<String> styles,
                        @Param("material") List<String> material,
                        @Param("category") List<String> categories, 
                        @Param("gender") List<String> gender,
                        @Param("price") List<String> price,
                        Pageable pageable);

        @Query("SELECT p FROM Products p WHERE p.shoes.name = :shoe_name")
        List<Products> findByShoesName(String shoe_name);

        @Query("SELECT p FROM Products p WHERE ( p.styles.style_name LIKE %:key% ) OR (p.materials.material_name LIKE %:key% ) OR (categories.category_name LIKE %:key% ) OR (p.shoes.name LIKE %:key%)")
        List<Products> SearchProduct(String key);
        /*
        @Query("SELECT i.quantity FROM Products p JOIN p.inventories i WHERE p.shoes.shoe_id = :shoe_id  AND i.sizes.size_id = :size_id" )
        int quantity(int shoe_id, int size_id);
         */

}
