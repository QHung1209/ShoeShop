package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Category;
import com.example.demo.domain.entity.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public boolean isExistsCategory(String name) {
        return this.categoryRepository.existsCategoryByName(name);
    }

    public Category createCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    public Category getCategoryById(long id) {
        Optional<Category> categoryOptional = this.categoryRepository.findById(id);
        return categoryOptional.isPresent() ? categoryOptional.get() : null;
    }

    public Category updateCategory(Category category) {
        Category update = this.getCategoryById(category.getId());
        update.setName(category.getName());
        update = this.categoryRepository.save(update);
        return update;
    }

    public void deleteCategory(long id) {
        Category category = this.getCategoryById(id);
        if (!category.getProducts().isEmpty()) {
            List<Product> listProducts = this.productRepository.findByCategory(category);
            this.productRepository.deleteAll(listProducts);
        }

        this.categoryRepository.deleteById(id);
    }

    public List<Category> getAllCategories()
    {
        return this.categoryRepository.findAll();
    }
}
