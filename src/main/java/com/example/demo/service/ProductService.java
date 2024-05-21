package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Inventory;
import com.example.demo.entity.Products;
import com.example.demo.dto.InventoryDTO;
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

    public static ProductDTO geProductDTO(Products data)
    {
        ProductDTO temp = new ProductDTO();
        temp.setProduct_id(data.getProduct_id());
        temp.setShoe_name(data.getShoes().getName());
        temp.setColor_name(data.getColors().getColor_name());
        temp.setCategory(data.getCategories().getCategory_name());
        temp.setDiscount(data.getDiscount());
        temp.setGender(data.getGenders().getGender_name());
        temp.setMaterial(data.getMaterials().getMaterial_name());
        temp.setStyle(data.getStyles().getStyle_name());
        temp.setImage_url(data.getImage());
        temp.setPrice(data.getShoes().getPrice());
        return temp;
    }
    @Override
    public List<ProductDTO> getAllProduct() {
        List<ProductDTO> ProductDTOs = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(0, Integer.MAX_VALUE);
        Page<Products> listData = ProductRepository.findAll(pageRequest);

        for (Products data : listData) {
            ProductDTOs.add(geProductDTO(data));
        }
        return ProductDTOs;
    }

    public List<ProductDTO> mapProductsToDTOs(Page<Products> productsPage) {
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Products data : productsPage) {
            productDTOs.add(geProductDTO(data));
        }
        return productDTOs;
    }

    public List<ProductDTO> filter(List<String> styles, List<String> material, List<String> categories, List<String> gender,
            List<String> prices) {
        List<ProductDTO> productDTOs = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(0, Integer.MAX_VALUE);

        if (prices == null) {
            productDTOs.addAll(mapProductsToDTOs(
                    ProductRepository.findProductsGT1000(styles, material, categories, gender,prices, pageRequest)));
            return productDTOs;
        }

        if (prices.contains("1000")) {
            productDTOs.addAll(mapProductsToDTOs(
                    ProductRepository.findProductsGT1000(styles, material, categories, gender,prices, pageRequest)));
        }
        if (prices.contains("600-999")) {
            productDTOs.addAll(mapProductsToDTOs(
                    ProductRepository.findProducts6_9(styles, material, categories, gender,prices, pageRequest)));
        }
        if (prices.contains("300-599")) {
            productDTOs.addAll(mapProductsToDTOs(
                    ProductRepository.findProducts3_5(styles, material, categories, gender,prices, pageRequest)));
        }

        return productDTOs;
    }

    @Override
    public ProductDTO detail(int id) {
        Optional<Products> productDetail = ProductRepository.findById(id);

        ProductDTO productDTO = new ProductDTO();
        if (productDetail.isPresent()) {

            productDTO = geProductDTO(productDetail.get());

            List<ProductDTO> related_product = new ArrayList<>();
            List<Products> related = ProductRepository.findByShoesName(productDTO.getShoe_name());
            for (Products re : related) {
                ProductDTO temp = new ProductDTO();
                temp.setColor_code(re.getColors().getColor_code());
                temp.setColor_name(re.getColors().getColor_name());
                temp.setProduct_id(re.getProduct_id());
                related_product.add(temp);
            }
            productDTO.setRelated_products(related_product);

            List<InventoryDTO> inventoryDTOs = new ArrayList<>();
            List<Inventory> inventories = productDetail.get().getListInventories();
            for (Inventory inv : inventories) {
                InventoryDTO temp = new InventoryDTO();
                temp.setInventory_id(inv.getInventory_id());
                temp.setProduct_id(inv.getProducts().getProduct_id());
                temp.setSize_id(inv.getSizes().getSize_id());
                temp.setSize_name(inv.getSizes().getSize_name());
                temp.setQuantity(inv.getQuantity());
                inventoryDTOs.add(temp);
            }
            productDTO.setInventoryDTOs(inventoryDTOs);
        }
        return productDTO;

    }

    @Override
    public Set<String> getStyle() {
        PageRequest pageRequest = PageRequest.of(0, Integer.MAX_VALUE);
        Page<Products> listData = ProductRepository.findAll(pageRequest);
        Set<String> styleSet = new HashSet<String>();
        for (Products data : listData) {
            styleSet.add(data.getStyles().getStyle_name());
        }
        return styleSet;

    }

    @Override
    public Set<String> getCategory() {
        PageRequest pageRequest = PageRequest.of(0, Integer.MAX_VALUE);
        Page<Products> listData = ProductRepository.findAll(pageRequest);
        Set<String> categorySet = new HashSet<String>();
        for (Products data : listData) {
            categorySet.add(data.getCategories().getCategory_name());
        }
        return categorySet;

    }

    @Override
    public Set<String> getMaterial() {
        PageRequest pageRequest = PageRequest.of(0, Integer.MAX_VALUE);
        Page<Products> listData = ProductRepository.findAll(pageRequest);
        Set<String> materialSet = new HashSet<String>();
        for (Products data : listData) {
            materialSet.add(data.getMaterials().getMaterial_name());
        }
        return materialSet;

    }
    /* 
    @Override
    public int getQuantity(int shoe_id, int size_id)
    {
        return ProductRepository.quantity(shoe_id, size_id);
    }*/
}
