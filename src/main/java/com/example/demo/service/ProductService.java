package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Products;
import com.example.demo.dto.ProductDTO;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.imp.ProductServiceImp;

@Service
public class ProductService implements ProductServiceImp {

    @Autowired
    ProductRepository ProductRepository;

    
    @Override
    public boolean insertProduct(int shoe_id, int color_id, int size_id, int gender_id, int style_id, int material_id,
            int category_id, int discount_id, int quantity_id, int price, String image_url) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertProduct'");
    }


    @Override
    public List<ProductDTO> getAllProduct() {
        List<ProductDTO> ProductDTOs = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(0, Integer.MAX_VALUE);
        Page<Products> listData = ProductRepository.findAll(pageRequest);

        for (Products data : listData) {
            ProductDTO temp = new ProductDTO();
            temp.setShoe_name(data.getShoes().getName());
            temp.setColor(data.getColors().getColor_name());
            temp.setSize(data.getSizes().getSize_name());
            temp.setCategory(data.getCategories().getCategory_name());
            temp.setDiscount(data.getDiscount());
            temp.setQuantity(data.getQuantity());
            temp.setGender(data.getGenders().getGender_name());
            temp.setMaterial(data.getMaterials().getMaterial_name());
            temp.setStyle(data.getStyles().getStyle_name());
            temp.setImage_url(data.getImage());
            temp.setPrice(data.getShoes().getPrice());
            ProductDTOs.add(temp);
        }
        return ProductDTOs;
    }

    @Override 
    public Set<String> getStyle()
    {
        PageRequest pageRequest = PageRequest.of(0, Integer.MAX_VALUE);
        Page<Products> listData = ProductRepository.findAll(pageRequest);
        Set<String> styleSet = new HashSet<String>();
        for(Products data: listData)
        {
            styleSet.add(data.getStyles().getStyle_name());
        }
        return styleSet;

    }

    @Override 
    public Set<String> getCategory()
    {
        PageRequest pageRequest = PageRequest.of(0, Integer.MAX_VALUE);
        Page<Products> listData = ProductRepository.findAll(pageRequest);
        Set<String> categorySet = new HashSet<String>();
        for(Products data: listData)
        {
            categorySet.add(data.getCategories().getCategory_name());
        }
        return categorySet;

    }

    @Override 
    public Set<String> getMaterial()
    {
        PageRequest pageRequest = PageRequest.of(0, Integer.MAX_VALUE);
        Page<Products> listData = ProductRepository.findAll(pageRequest);
        Set<String> materialSet = new HashSet<String>();
        for(Products data: listData)
        {
            materialSet.add(data.getMaterials().getMaterial_name());
        }
        return materialSet;

    }
}
