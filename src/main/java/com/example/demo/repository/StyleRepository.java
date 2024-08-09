package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.Style;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long>, JpaSpecificationExecutor<Style>{
    boolean existsStyleByName(String name);
}
