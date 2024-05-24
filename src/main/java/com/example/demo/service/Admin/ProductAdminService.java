package com.example.demo.service.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.ProductDTO;
import com.example.demo.repository.Admin.ProductAdminRepository;
import com.example.demo.service.Admin.imp.ProductAdminServiceImp;
import java.util.List;

@Service
public class ProductAdminService implements ProductAdminServiceImp {

    @Autowired
    public ProductAdminRepository productAdminRepository;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productAdminRepository.getAllProducts();
    }
}
