package com.example.demo.service.imp;

import java.util.List;
import java.util.Set;

import com.example.demo.dto.ProductDTO;

public interface ProductServiceImp {
    boolean insertProduct(
    int shoe_id,
    int color_id,
    int size_id,
    int gender_id,
    int style_id, 
    int material_id,
    int category_id,
    int discount_id,
    int quantity_id,
    int price,
    String image_url);

    List<ProductDTO> getAllProduct();
    Set<String> getStyle();
    Set<String> getCategory();
    Set<String> getMaterial();
}
     