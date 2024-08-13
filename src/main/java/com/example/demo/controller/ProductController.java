package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entity.Product;
import com.example.demo.domain.response.ResultPaginationDTO;
import com.example.demo.domain.response.product.ResCreateProductDTO;
import com.example.demo.domain.response.product.ResGetProductDTO;
import com.example.demo.domain.response.product.ResUpdateProductDTO;
import com.example.demo.service.CategoryService;
import com.example.demo.service.MaterialService;
import com.example.demo.service.ProductService;
import com.example.demo.service.ShoeService;
import com.example.demo.service.StyleService;
import com.example.demo.util.error.IdInvalidException;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
public class ProductController {
    private final ProductService productService;
    private final StyleService styleService;
    private final MaterialService materialService;
    private final ShoeService shoeService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, StyleService styleService, MaterialService materialService,
            ShoeService shoeService, CategoryService categoryService) {
        this.productService = productService;
        this.styleService = styleService;
        this.materialService = materialService;
        this.shoeService = shoeService;
        this.categoryService = categoryService;
    }

    @PostMapping("/products")
    public ResponseEntity<ResCreateProductDTO> createProduct(@Valid @RequestBody Product product)
            throws IdInvalidException {
        if (this.categoryService.getCategoryById(product.getCategory().getId()) == null)
            throw new IdInvalidException("Category id = " + product.getCategory().getId() + "doesn't exist.");
        if (this.styleService.getStyleById(product.getStyle().getId()) == null)
            throw new IdInvalidException("Style id = " + product.getStyle().getId() + "doesn't exist.");
        if (this.materialService.getMaterialById(product.getMaterial().getId()) == null)
            throw new IdInvalidException("Material id = " + product.getMaterial().getId() + "doesn't exist.");
        if (this.shoeService.getShoeById(product.getShoe().getId()) == null)
            throw new IdInvalidException("Shoe id = " + product.getShoe().getId() + "doesn't exist.");

        return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.createProduct(product));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ResGetProductDTO> getProduct(@PathVariable("id") long id)
            throws IdInvalidException {
        Product product = this.productService.getProductById(id);
        if (product == null)
            throw new IdInvalidException("Product id = " + id + "doesn't exist.");
        return ResponseEntity.ok(this.productService.convertToResProductDTO(product));
    }

    @PutMapping("/products")
    public ResponseEntity<ResUpdateProductDTO> updateProduct(@Valid @RequestBody Product product)
            throws IdInvalidException {
        if (this.productService.getProductById(product.getId()) == null)
            throw new IdInvalidException("Product id = " + product.getId() + "doesn't exist.");
        if (this.categoryService.getCategoryById(product.getCategory().getId()) == null)
            throw new IdInvalidException("Category id = " + product.getCategory().getId() + "doesn't exist.");
        if (this.styleService.getStyleById(product.getStyle().getId()) == null)
            throw new IdInvalidException("Style id = " + product.getStyle().getId() + "doesn't exist.");
        if (this.materialService.getMaterialById(product.getMaterial().getId()) == null)
            throw new IdInvalidException("Material id = " + product.getMaterial().getId() + "doesn't exist.");
        if (this.shoeService.getShoeById(product.getShoe().getId()) == null)
            throw new IdInvalidException("Shoe id = " + product.getShoe().getId() + "doesn't exist.");

        return ResponseEntity.ok(this.productService.updateProduct(product));
    }

    @GetMapping("/products/filter")
    public ResponseEntity<ResultPaginationDTO> ListProduct(
            @RequestParam(value = "style", required = false) String style,
            @RequestParam(value = "material", required = false) String material,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "price", required = false) String price,
            Pageable pageable) {

        List<String> styles = style != null ? Arrays.asList(style.split(",")) : null;
        List<String> materials = material != null ? Arrays.asList(material.split(",")) : null;
        List<String> categories = category != null ? Arrays.asList(category.split(",")) : null;
        List<String> genders = gender != null ? Arrays.asList(gender.split(",")) : null;
        List<String> prices = price != null ? Arrays.asList(price.split(",")) : null;

        return ResponseEntity
                .ok(this.productService.filterProduct(styles, materials, categories, genders, prices, pageable));
    }

}
