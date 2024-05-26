package com.example.demo.service.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ProductDTO;
import com.example.demo.repository.Admin.ProductAdminRepository;
import com.example.demo.service.Admin.imp.ProductAdminServiceImp;
import java.util.List;
import com.example.demo.entity.Products;

@Service
public class ProductAdminService implements ProductAdminServiceImp {

    @Autowired
    public ProductAdminRepository productAdminRepository;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productAdminRepository.getAllProducts();
    }

    @Override
    public Products addProduct(String shoeName, String colorName, String genderName, String styleName,
            String materialName,
            String categoryName, int discount, MultipartFile imageFile) {
        return productAdminRepository.addProduct(shoeName, colorName, genderName, styleName, materialName, categoryName,
                discount, imageFile);
    }

    @Override
    public Products updateProduct(int productId, String shoeName, String colorName, String genderName, String styleName,
            String materialName, String categoryName, Integer discount, MultipartFile imageFile) {
        return productAdminRepository.updateProduct(productId, shoeName, colorName, genderName, styleName, materialName,
                categoryName, discount, imageFile);
    }
}