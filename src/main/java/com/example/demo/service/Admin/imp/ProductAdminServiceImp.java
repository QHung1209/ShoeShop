package com.example.demo.service.Admin.imp;

import com.example.demo.dto.ProductDTO;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Products;

public interface ProductAdminServiceImp {

    public List<ProductDTO> getAllProducts();

    public Products addProduct(String shoeName, String colorName, String genderName, String styleName,
            String materialName, String categoryName, int discount, MultipartFile imageFile);

    public Products updateProduct(int productId, String shoeName, String colorName, String genderName, String styleName, String materialName, String categoryName, Integer discount, MultipartFile imageFile);

    public String saveImage(MultipartFile file, int productId);

}
