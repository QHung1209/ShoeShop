package com.example.demo.repository.Admin.imp;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Products;

public interface ProductAdminRepositoryImp {

    public List<ProductDTO> getAllProducts();

    public ProductDTO convertToDTO(Products product);

    public Products addProduct(String shoeName, String colorName, String genderName, String styleName,
            String materialName, String categoryName, int discount, MultipartFile imageFile);

    public Products updateProduct(int productId, String shoeName, String colorName, String genderName, String styleName,
            String materialName, String categoryName, Integer discount, MultipartFile imageFile);
}
