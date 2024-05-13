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

        @Query("SELECT p FROM Products p JOIN p.materials m JOIN p.styles s JOIN p.categories c JOIN p.shoes sh WHERE (:style IS NULL OR s.style_name IN :style) AND (:material IS NULL OR m.material_name IN :material) AND (:category IS NULL OR c.category_name IN :category) AND (:price IS NULL OR sh.price >= 1000000) ")
        public Page<Products> findProductsGT1000(@Param("style") List<String> styles,
                        @Param("material") List<String> material, @Param("category") List<String> categories,
                        @Param("price") List<String> price,
                        Pageable pageable);

        @Query("SELECT p FROM Products p JOIN p.materials m JOIN p.styles s JOIN p.categories c JOIN p.shoes sh WHERE (:style IS NULL OR s.style_name IN :style) AND (:material IS NULL OR m.material_name IN :material) AND (:category IS NULL OR c.category_name IN :category) AND (:price IS NULL OR sh.price BETWEEN 600000 AND 999999) ")
        public Page<Products> findProducts6_9(@Param("style") List<String> styles,
                        @Param("material") List<String> material,
                        @Param("category") List<String> categories, @Param("price") List<String> price,
                        Pageable pageable);

        @Query("SELECT p FROM Products p JOIN p.materials m JOIN p.styles s JOIN p.categories c JOIN p.shoes sh WHERE (:style IS NULL OR s.style_name IN :style) AND (:material IS NULL OR m.material_name IN :material) AND (:category IS NULL OR c.category_name IN :category) AND (:price IS NULL OR sh.price BETWEEN 300000 AND 599999) ")
        public Page<Products> findProducts3_5(@Param("style") List<String> styles,
                        @Param("material") List<String> material,
                        @Param("category") List<String> categories, @Param("price") List<String> price,
                        Pageable pageable);

        @Query("SELECT p FROM Products p WHERE p.shoes.name = :shoe_name")
        List<Products> findByShoesName(String shoe_name);
        

}
