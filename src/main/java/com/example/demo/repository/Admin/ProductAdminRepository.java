package com.example.demo.repository.Admin;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Products;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ProductAdminRepository {
    @Autowired
    private EntityManager entityManager;

    public List<ProductDTO> getAllProducts() {
        String jpql = "SELECT p FROM Products p";
        TypedQuery<Products> query = entityManager.createQuery(jpql, Products.class);
        List<Products> products = query.getResultList();
        return products.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ProductDTO convertToDTO(Products product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProduct_id(product.getProduct_id());

        if (product.getShoes() != null) {
            productDTO.setShoe_name(product.getShoes().getName());
        }

        if (product.getColors() != null) {
            productDTO.setColor_name(product.getColors().getColor_name());
        }

        if (product.getGenders() != null) {
            productDTO.setGender(product.getGenders().getGender_name());
        }

        if (product.getStyles() != null) {
            productDTO.setStyle(product.getStyles().getStyle_name());
        }

        if (product.getMaterials() != null) {
            productDTO.setMaterial(product.getMaterials().getMaterial_name());
        }

        if (product.getCategories() != null) {
            productDTO.setCategory(product.getCategories().getCategory_name());
        }

        productDTO.setDiscount(product.getDiscount());
        productDTO.setImage_url(product.getImage_url());

        // You may set other properties like price, related_products, inventoryDTOs if
        // needed

        return productDTO;
    }
}
