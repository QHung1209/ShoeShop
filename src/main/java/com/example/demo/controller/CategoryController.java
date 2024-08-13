package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entity.Category;
import com.example.demo.service.CategoryService;
import com.example.demo.util.error.IdInvalidException;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) throws IdInvalidException {
        if (this.categoryService.isExistsCategory(category.getName()))
            throw new IdInvalidException("The category name exists.");
        return ResponseEntity.status(HttpStatus.CREATED).body(this.categoryService.createCategory(category));
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable("id") long id) throws IdInvalidException {
        Category category = this.categoryService.getCategoryById(id);
        if (category == null)
            throw new IdInvalidException("Category id = " + id + " doesn't exist.");
        return ResponseEntity.ok(category);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategory() {
        return ResponseEntity.ok(this.categoryService.getAllCategories());
    }

    @PutMapping("/categories")
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category) throws IdInvalidException {
        if (this.categoryService.getCategoryById(category.getId()) == null)
            throw new IdInvalidException("Category id = " + category.getId() + " doesn't exist.");

        return ResponseEntity.ok(this.categoryService.updateCategory(category));
    }

    
}
