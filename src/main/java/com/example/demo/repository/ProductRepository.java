package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.Category;
import com.example.demo.domain.entity.Color;
import com.example.demo.domain.entity.Material;
import com.example.demo.domain.entity.Product;
import com.example.demo.domain.entity.Size;
import com.example.demo.domain.entity.Style;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

        public List<Product> findByColor(Color color);

        public List<Product> findByStyle(Style style);

        public List<Product> findByMaterial(Material material);

        public List<Product> findByCategory(Category category);

        public List<Product> findBySize(Size size);

        @Query("SELECT p FROM Products p  WHERE (:style IS NULL OR p.styles.style_name IN :style) AND (:material IS NULL OR p.materials.material_name IN :material) AND (:category IS NULL OR p.categories.category_name IN :category) AND (:gender IS NULL OR p.genders.gender_name IN :gender) AND (:price IS NULL OR p.shoes.price >= 1000000) ")
        public Page<Product> findProductsGT1K(@Param("style") List<String> styles,
                        @Param("material") List<String> material, @Param("category") List<String> categories,
                        @Param("gender") List<String> gender,
                        @Param("price") List<String> price,
                        Pageable pageable);

        @Query("SELECT p FROM Products p WHERE (:style IS NULL OR p.styles.style_name IN :style) AND (:material IS NULL OR p.materials.material_name IN :material) AND (:category IS NULL OR p.categories.category_name IN :category) AND (:gender IS NULL OR p.genders.gender_name IN :gender) AND (:price IS NULL OR p.shoes.price BETWEEN 600000 AND 999999) ")
        public Page<Product> findProductsBetween6N9(@Param("style") List<String> styles,
                        @Param("material") List<String> material,
                        @Param("category") List<String> categories, @Param("gender") List<String> gender,
                        @Param("price") List<String> price,
                        Pageable pageable);

        @Query("SELECT p FROM Products p WHERE (:style IS NULL OR p.styles.style_name IN :style) AND (:material IS NULL OR p.materials.material_name IN :material) AND (:category IS NULL OR p.categories.category_name IN :category) AND (:gender IS NULL OR p.genders.gender_name IN :gender) AND (:price IS NULL OR p.shoes.price BETWEEN 300000 AND 599999) ")
        public Page<Product> findProductsBetween3N5(@Param("style") List<String> styles,
                        @Param("material") List<String> material,
                        @Param("category") List<String> categories,
                        @Param("gender") List<String> gender,
                        @Param("price") List<String> price,
                        Pageable pageable);

        @Query("SELECT p FROM Products p WHERE ( p.styles.style_name LIKE %:key% ) OR (p.materials.material_name LIKE %:key% ) OR (categories.category_name LIKE %:key% ) OR (p.shoes.name LIKE %:key%) OR (p.colors.color_name LIKE %:key%)")
        List<Product> SearchProduct(String key);

        @Query("SELECT p FROM Products p WHERE p.discount > 0")
        List<Product> saleOff();

}
