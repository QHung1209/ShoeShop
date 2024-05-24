package com.example.demo.repository.Admin.imp;

import java.util.List;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Products;

public interface ProductAdminRepositoryImp {

    public List<ProductDTO> getAllProducts();

    public ProductDTO convertToDTO(Products product);
}
