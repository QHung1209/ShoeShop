package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Category;

@Service
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category>{
    boolean existsCategoryByName(String name);
}
