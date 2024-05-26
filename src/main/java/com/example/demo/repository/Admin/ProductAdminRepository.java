package com.example.demo.repository.Admin;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Categories;
import com.example.demo.entity.Colors;
import com.example.demo.entity.Genders;
import com.example.demo.entity.Materials;
import com.example.demo.entity.Products;
import com.example.demo.entity.Shoes;
import com.example.demo.entity.Styles;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProductAdminRepository {
    @Autowired
    private EntityManager entityManager;

    public List<ProductDTO> getAllProducts() {
        String jpql = "SELECT p FROM Products p";
        TypedQuery<Products> query = entityManager.createQuery(jpql, Products.class);
        List<Products> products = query.getResultList();
        return products.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ProductDTO convertToDTO(Products product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProduct_id(product.getProduct_id());

        if (product.getShoes() != null) {
            productDTO.setShoe_name(product.getShoes().getName());
        }

        if (product.getColors() != null) {
            productDTO.setColor_name(product.getColors().getColor_name());
        }

        if (product.getGenders() != null) {
            productDTO.setGender(product.getGenders().getGender_name());
        }

        if (product.getStyles() != null) {
            productDTO.setStyle(product.getStyles().getStyle_name());
        }

        if (product.getMaterials() != null) {
            productDTO.setMaterial(product.getMaterials().getMaterial_name());
        }

        if (product.getCategories() != null) {
            productDTO.setCategory(product.getCategories().getCategory_name());
        }

        productDTO.setDiscount(product.getDiscount());
        productDTO.setImage_url(product.getImage_url());

        // You may set other properties like price, related_products, inventoryDTOs if
        // needed

        return productDTO;
    }

    @Transactional
    public Products addProduct(String shoeName, String colorName, String genderName, String styleName,
            String materialName, String categoryName, int discount, MultipartFile imageFile) {
        // Fetch the existing shoe entity by name
        TypedQuery<Shoes> shoeQuery = entityManager.createQuery(
                "SELECT s FROM Shoes s WHERE s.name = :shoeName", Shoes.class);
        shoeQuery.setParameter("shoeName", shoeName);
        Shoes shoe = shoeQuery.getSingleResult();

        // Fetch the existing color entity by name
        TypedQuery<Colors> colorQuery = entityManager.createQuery(
                "SELECT c FROM Colors c WHERE c.color_name = :colorName", Colors.class);
        colorQuery.setParameter("colorName", colorName);
        Colors color = colorQuery.getSingleResult();

        // Fetch the existing gender entity by name
        TypedQuery<Genders> genderQuery = entityManager.createQuery(
                "SELECT g FROM Genders g WHERE g.gender_name = :genderName", Genders.class);
        genderQuery.setParameter("genderName", genderName);
        Genders gender = genderQuery.getSingleResult();

        // Fetch the existing style entity by name
        TypedQuery<Styles> styleQuery = entityManager.createQuery(
                "SELECT st FROM Styles st WHERE st.style_name = :styleName", Styles.class);
        styleQuery.setParameter("styleName", styleName);
        Styles style = styleQuery.getSingleResult();

        // Fetch the existing material entity by name
        TypedQuery<Materials> materialQuery = entityManager.createQuery(
                "SELECT m FROM Materials m WHERE m.material_name = :materialName", Materials.class);
        materialQuery.setParameter("materialName", materialName);
        Materials material = materialQuery.getSingleResult();

        // Fetch the existing category entity by name
        TypedQuery<Categories> categoryQuery = entityManager.createQuery(
                "SELECT c FROM Categories c WHERE c.category_name = :categoryName", Categories.class);
        categoryQuery.setParameter("categoryName", categoryName);
        Categories category = categoryQuery.getSingleResult();

        // Create a new product
        Products product = new Products();
        product.setShoes(shoe);
        product.setColors(color);
        product.setGenders(gender);
        product.setStyles(style);
        product.setMaterials(material);
        product.setCategories(category);
        product.setDiscount(discount);

        // Save the image file and set the image URL
        String imageUrl = saveImage(imageFile);
        product.setImage_url(imageUrl);

        // Persist the new product
        entityManager.persist(product);

        return product;
    }

    @Transactional
    public Products updateProduct(int productId, String shoeName, String colorName, String genderName, String styleName,
            String materialName, String categoryName, Integer discount, MultipartFile imageFile) {
        // Fetch the existing product entity by id
        Products product = entityManager.find(Products.class, productId);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        // Update shoe if shoeName is provided
        if (shoeName != null) {
            TypedQuery<Shoes> shoeQuery = entityManager.createQuery(
                    "SELECT s FROM Shoes s WHERE s.name = :shoeName", Shoes.class);
            shoeQuery.setParameter("shoeName", shoeName);
            Shoes shoe = shoeQuery.getSingleResult();
            product.setShoes(shoe);
        }

        // Update color if colorName is provided
        if (colorName != null) {
            TypedQuery<Colors> colorQuery = entityManager.createQuery(
                    "SELECT c FROM Colors c WHERE c.color_name = :colorName", Colors.class);
            colorQuery.setParameter("colorName", colorName);
            Colors color = colorQuery.getSingleResult();
            product.setColors(color);
        }

        // Update gender if genderName is provided
        if (genderName != null) {
            TypedQuery<Genders> genderQuery = entityManager.createQuery(
                    "SELECT g FROM Genders g WHERE g.gender_name = :genderName", Genders.class);
            genderQuery.setParameter("genderName", genderName);
            Genders gender = genderQuery.getSingleResult();
            product.setGenders(gender);
        }

        // Update style if styleName is provided
        if (styleName != null) {
            TypedQuery<Styles> styleQuery = entityManager.createQuery(
                    "SELECT st FROM Styles st WHERE st.style_name = :styleName", Styles.class);
            styleQuery.setParameter("styleName", styleName);
            Styles style = styleQuery.getSingleResult();
            product.setStyles(style);
        }

        // Update material if materialName is provided
        if (materialName != null) {
            TypedQuery<Materials> materialQuery = entityManager.createQuery(
                    "SELECT m FROM Materials m WHERE m.material_name = :materialName", Materials.class);
            materialQuery.setParameter("materialName", materialName);
            Materials material = materialQuery.getSingleResult();
            product.setMaterials(material);
        }

        // Update category if categoryName is provided
        if (categoryName != null) {
            TypedQuery<Categories> categoryQuery = entityManager.createQuery(
                    "SELECT c FROM Categories c WHERE c.category_name = :categoryName", Categories.class);
            categoryQuery.setParameter("categoryName", categoryName);
            Categories category = categoryQuery.getSingleResult();
            product.setCategories(category);
        }

        // Update discount if provided
        if (discount != null) {
            product.setDiscount(discount);
        }

        // Update image if imageFile is provided
        if (imageFile != null && !imageFile.isEmpty()) {
            String imageUrl = saveImage(imageFile);
            product.setImage_url(imageUrl);
        }

        // Persist the updated product
        entityManager.merge(product);

        return product;
    }

    private String saveImage(MultipartFile imageFile) {
        String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
        Path directoryPath = Paths.get("java/uploads");
        Path imagePath = directoryPath.resolve(fileName);
        try {
            // Ensure the directory exists
            Files.createDirectories(directoryPath);
            // Save the file
            Files.copy(imageFile.getInputStream(), imagePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
        return imagePath.toString(); // Or return the URL if using a storage service
    }

}
