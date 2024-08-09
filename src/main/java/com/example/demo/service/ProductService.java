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

import com.example.demo.domain.entity.Image;
import com.example.demo.domain.entity.Inventory;
import com.example.demo.domain.entity.Product;
import com.example.demo.dto.ImageDTO;
import com.example.demo.dto.InventoryDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.imp.ProductServiceImp;

@Service
public class ProductService implements ProductServiceImp {

    @Autowired
    ProductRepository ProductRepository;

    @Autowired
    InventoryRepository inventoryReposity;

    @Override
    public boolean insertProduct(int shoe_id, int color_id, int size_id, int gender_id, int style_id, int material_id,
            int category_id, int discount_id, int quantity_id, int price, String image_url) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertProduct'");
    }

    public static ProductDTO geProductDTO(Product data) {
        ProductDTO temp = new ProductDTO();
        temp.setProduct_id(data.getProduct_id());
        temp.setShoe_name(data.getShoes().getName());
        temp.setColor_name(data.getColors().getColor_name());
        temp.setCategory(data.getCategories().getCategory_name());
        temp.setDiscount(data.getDiscount());
        temp.setGender(data.getGenders().getGender_name());
        temp.setMaterial(data.getMaterials().getMaterial_name());
        temp.setStyle(data.getStyles().getStyle_name());
        temp.setColor_code(data.getColors().getColor_code());
        temp.setImage_url(data.getImage());
        temp.setPrice(data.getShoes().getPrice());
        return temp;
    }

    @Override
    public List<ProductDTO> getAllProduct() {
        List<ProductDTO> ProductDTOs = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(0, Integer.MAX_VALUE);
        Page<Product> listData = ProductRepository.findAll(pageRequest);

        for (Product data : listData) {
            ProductDTOs.add(geProductDTO(data));
        }
        return ProductDTOs;
    }

    public List<ProductDTO> mapProductsToDTOs(Page<Product> productsPage) {
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product data : productsPage) {
            productDTOs.add(geProductDTO(data));
        }
        return productDTOs;
    }

    public List<ProductDTO> filter(List<String> styles, List<String> material, List<String> categories,
            List<String> gender,
            List<String> prices) {
        List<ProductDTO> productDTOs = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(0, Integer.MAX_VALUE);

        if (prices == null) {
            productDTOs.addAll(mapProductsToDTOs(
                    ProductRepository.findProductsGT1000(styles, material, categories, gender, prices, pageRequest)));
            return productDTOs;
        }

        if (prices.contains("1000")) {
            productDTOs.addAll(mapProductsToDTOs(
                    ProductRepository.findProductsGT1000(styles, material, categories, gender, prices, pageRequest)));
        }
        if (prices.contains("600-999")) {
            productDTOs.addAll(mapProductsToDTOs(
                    ProductRepository.findProducts6_9(styles, material, categories, gender, prices, pageRequest)));
        }
        if (prices.contains("300-599")) {
            productDTOs.addAll(mapProductsToDTOs(
                    ProductRepository.findProducts3_5(styles, material, categories, gender, prices, pageRequest)));
        }

        return productDTOs;
    }

    @Override
    public ProductDTO detail(int id) {
        Optional<Product> productDetail = ProductRepository.findById(id);

        ProductDTO productDTO = new ProductDTO();
        if (productDetail.isPresent()) {

            productDTO = geProductDTO(productDetail.get());

            List<ProductDTO> related_product = new ArrayList<>();
            List<Product> related = ProductRepository.findByShoesName(productDTO.getShoe_name());
            for (Product re : related) {
                ProductDTO temp = geProductDTO(re);
                related_product.add(temp);
            }
            productDTO.setRelated_products(related_product);

            List<InventoryDTO> inventoryDTOs = new ArrayList<>();
            List<Inventory> inventories = productDetail.get().getListInventories();
            for (Inventory inv : inventories) {
                InventoryDTO temp = InventoryService.getInventoryDTO(inv);
                inventoryDTOs.add(temp);
            }
            productDTO.setInventoryDTOs(inventoryDTOs);

            List<ImageDTO> imageDTOs = new ArrayList<>();
            List<Image> images = productDetail.get().getListImages();
            for (Image img : images) {
                ImageDTO temp = new ImageDTO();
                temp.setImage_id(img.getImage_id());
                temp.setImage_url(img.getImage_url());
                temp.setProduct_id(img.getProducts().getProduct_id());
                imageDTOs.add(temp);
            }
            productDTO.setImageDTOs(imageDTOs);

        }
        return productDTO;

    }

    @Override
    public Set<String> getStyle() {
        PageRequest pageRequest = PageRequest.of(0, Integer.MAX_VALUE);
        Page<Product> listData = ProductRepository.findAll(pageRequest);
        Set<String> styleSet = new HashSet<String>();
        for (Product data : listData) {
            styleSet.add(data.getStyles().getStyle_name());
        }
        return styleSet;

    }

    @Override
    public Set<String> getCategory() {
        PageRequest pageRequest = PageRequest.of(0, Integer.MAX_VALUE);
        Page<Product> listData = ProductRepository.findAll(pageRequest);
        Set<String> categorySet = new HashSet<String>();
        for (Product data : listData) {
            categorySet.add(data.getCategories().getCategory_name());
        }
        return categorySet;

    }

    @Override
    public Set<String> getMaterial() {
        PageRequest pageRequest = PageRequest.of(0, Integer.MAX_VALUE);
        Page<Product> listData = ProductRepository.findAll(pageRequest);
        Set<String> materialSet = new HashSet<String>();
        for (Product data : listData) {
            materialSet.add(data.getMaterials().getMaterial_name());
        }
        return materialSet;

    }
    /*
     * @Override
     * public int getQuantity(int shoe_id, int size_id)
     * {
     * return ProductRepository.quantity(shoe_id, size_id);
     * }
     */

    @Override
    public List<ProductDTO> searchProduct(String key) {
        List<ProductDTO> productDTOs = new ArrayList<>();
        List<Product> lProducts = ProductRepository.SearchProduct(key);

        for (Product data : lProducts) {
            productDTOs.add(geProductDTO(data));
        }
        return productDTOs;
    }

    @Override
    public List<ProductDTO> saleOff() {
        List<ProductDTO> productDTOs = new ArrayList<>();
        List<Product> lProducts = ProductRepository.saleOff();
        for (Product data : lProducts) {
            productDTOs.add(geProductDTO(data));
        }
        return productDTOs;
    }

    @Override
    public List<ProductDTO> top5MostSell() {
        List<Product> listProducts = inventoryReposity.findMostSell();
        List<ProductDTO> lProductDTOs = new ArrayList<>();
        for(Product data : listProducts)
        {
            lProductDTOs.add(geProductDTO(data));
        }
        return lProductDTOs;
    }
}
