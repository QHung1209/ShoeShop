package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.example.demo.domain.entity.Image;
import com.example.demo.domain.entity.Inventory;
import com.example.demo.domain.entity.Product;
import com.example.demo.domain.response.ResultPaginationDTO;
import com.example.demo.domain.response.product.ResCreateProductDTO;
import com.example.demo.domain.response.product.ResGetProductDTO;
import com.example.demo.domain.response.product.ResUpdateProductDTO;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(long id) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);
        return optionalProduct.isPresent() ? optionalProduct.get() : null;
    }

    public ResCreateProductDTO convertToCreateProductDTO(Product product) {
        ResCreateProductDTO.ProductColor color = new ResCreateProductDTO.ProductColor(product.getColor().getName(),
                product.getColor().getCode());
        ResCreateProductDTO.ProductMaterial material = new ResCreateProductDTO.ProductMaterial(
                product.getMaterial().getName());
        ResCreateProductDTO.ProductShoe shoe = new ResCreateProductDTO.ProductShoe(product.getShoe().getName(),
                product.getShoe().getPrice());
        ResCreateProductDTO.ProductStyle style = new ResCreateProductDTO.ProductStyle(product.getStyle().getName());
        ResCreateProductDTO.ProductCategory category = new ResCreateProductDTO.ProductCategory(
                product.getCategory().getName());
        ResCreateProductDTO productDTO = new ResCreateProductDTO();
        productDTO.setColor(color);
        productDTO.setCategory(category);
        productDTO.setMaterial(material);
        productDTO.setStyle(style);
        productDTO.setShoe(shoe);
        productDTO.setId(product.getId());
        productDTO.setGender(product.getGender());
        productDTO.setUrl(product.getUrl());
        productDTO.setCreatedAt(product.getCreatedAt());
        productDTO.setCreatedBy(product.getCreatedBy());
        return productDTO;
    }

    public ResCreateProductDTO createProduct(Product product) {
        this.productRepository.save(product);

        return convertToCreateProductDTO(product);
    }

    public ResUpdateProductDTO updateProduct(Product product) {
        Product update = this.getProductById(product.getId());
        ResUpdateProductDTO res = new ResUpdateProductDTO();
        if (product.getCategory() != null) {
            update.setCategory(product.getCategory());
            ResCreateProductDTO.ProductCategory category = new ResCreateProductDTO.ProductCategory(
                    product.getCategory().getName());
            res.setCategory(category);
        }

        if (product.getColor() != null) {
            update.setColor(product.getColor());
            ResCreateProductDTO.ProductColor color = new ResCreateProductDTO.ProductColor(product.getColor().getName(),
                    product.getColor().getCode());
            res.setColor(color);
        }
        if (product.getMaterial() != null) {
            update.setMaterial(product.getMaterial());
            ResCreateProductDTO.ProductMaterial material = new ResCreateProductDTO.ProductMaterial(
                    product.getMaterial().getName());
            res.setMaterial(material);
        }
        if (product.getShoe() != null) {
            update.setShoe(product.getShoe());
            ResCreateProductDTO.ProductShoe shoe = new ResCreateProductDTO.ProductShoe(product.getShoe().getName(),
                    product.getShoe().getPrice());
            res.setShoe(shoe);
        }
        if (product.getStyle() != null) {
            update.setStyle(product.getStyle());
            ResCreateProductDTO.ProductStyle style = new ResCreateProductDTO.ProductStyle(product.getStyle().getName());
            res.setStyle(style);
        }

        update.setDiscount(product.getDiscount());
        update.setGender(product.getGender());
        update.setUrl(product.getUrl());

        this.productRepository.save(update);

        res.setGender(product.getGender());
        res.setId(product.getId());
        res.setUpdatedAt(product.getUpdatedAt());
        res.setUpdatedBy(product.getUpdatedBy());
        res.setUrl(product.getUrl());

        return res;
    }

    public ResGetProductDTO convertToResProductDTO(Product product) {

        ResGetProductDTO res = new ResGetProductDTO();
        ResCreateProductDTO.ProductColor color = new ResCreateProductDTO.ProductColor(product.getColor().getName(),
                product.getColor().getCode());
        ResCreateProductDTO.ProductMaterial material = new ResCreateProductDTO.ProductMaterial(
                product.getMaterial().getName());
        ResCreateProductDTO.ProductShoe shoe = new ResCreateProductDTO.ProductShoe(product.getShoe().getName(),
                product.getShoe().getPrice());
        ResCreateProductDTO.ProductStyle style = new ResCreateProductDTO.ProductStyle(product.getStyle().getName());
        ResCreateProductDTO.ProductCategory category = new ResCreateProductDTO.ProductCategory(
                product.getCategory().getName());

        res.setGender(product.getGender());
        res.setId(product.getId());
        res.setUpdatedAt(product.getUpdatedAt());
        res.setUpdatedBy(product.getUpdatedBy());
        res.setUrl(product.getUrl());
        res.setCreatedAt(product.getCreatedAt());
        res.setCreatedBy(product.getCreatedBy());
        res.setCategory(category);
        res.setStyle(style);
        res.setShoe(shoe);
        res.setMaterial(material);
        res.setColor(color);

        List<Inventory> inventories = product.getInventories();

        if (!inventories.isEmpty()) {
            List<ResGetProductDTO.ProductInventory> listInventories = new ArrayList<>();
            listInventories.addAll(inventories.stream()
                    .map(inventory -> new ResGetProductDTO.ProductInventory(
                            inventory.getId(),
                            inventory.getQuantity(),
                            Integer.parseInt(inventory.getSize().getName())))
                    .collect(Collectors.toList()));
            res.setInventories(listInventories);
        }

        List<Image> images = product.getImages();
        if (!images.isEmpty()) {
            List<ResGetProductDTO.ProductImage> listImages = new ArrayList<>();
            listImages.addAll(images.stream().map(image -> new ResGetProductDTO.ProductImage(image.getUrl()))
                    .collect(Collectors.toList()));
            res.setImages(listImages);
        }
        return res;

    }

    public ResultPaginationDTO pagination(Page<Product> pageProduct, Pageable pageable) {
        ResultPaginationDTO rs = new ResultPaginationDTO();
        ResultPaginationDTO.Meta mt = new ResultPaginationDTO.Meta();

        mt.setPage(pageable.getPageNumber() + 1);
        mt.setPageSize(pageable.getPageSize());

        mt.setPages(pageProduct.getTotalPages());
        mt.setTotal(pageProduct.getTotalPages());

        rs.setMeta(mt);

        List<Product> products = pageProduct.getContent();
        List<ResGetProductDTO> res = new ArrayList<>();
        res.addAll(products.stream().map(p -> this.convertToResProductDTO(p)).collect(Collectors.toList()));
        rs.setResult(res);
        return rs;
    }

    public ResultPaginationDTO getAllProduct(Specification<Product> specification, Pageable pageable) {
        Page<Product> pageProduct = this.productRepository.findAll(specification, pageable);
        return pagination(pageProduct, pageable);
    }

    public ResultPaginationDTO filterProduct(List<String> styles, List<String> material,
            List<String> categories,
            List<String> gender,
            List<String> prices, Pageable pageable) {

        if (prices == null) {
            Page<Product> pageProduct = this.productRepository.findProductsGT1K(styles, material, categories, gender,
                    prices, pageable);
            return pagination(pageProduct, pageable);
        }

        Page<Product> pageProduct = null;
        List<Product> allProducts = new ArrayList<>();

        if (prices.contains("1000")) {
            pageProduct = this.productRepository.findProductsGT1K(styles, material, categories, gender,
                    prices, pageable);
            allProducts.addAll(pageProduct.getContent());
        }
        if (prices.contains("600-999")) {
            pageProduct = this.productRepository.findProductsBetween6N9(styles, material, categories, gender,
                    prices, pageable);
            allProducts.addAll(pageProduct.getContent());
        }
        if (prices.contains("300-599")) {
            pageProduct = this.productRepository.findProductsBetween3N5(styles, material, categories, gender,
                    prices, pageable);
            allProducts.addAll(pageProduct.getContent());
        }
        pageProduct = new PageImpl<>(allProducts, pageable, allProducts.size());

        return pagination(pageProduct, pageable);
    }

}
