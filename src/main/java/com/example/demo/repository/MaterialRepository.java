package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Material;

@Service
public interface MaterialRepository extends JpaRepository<Material, Long>, JpaSpecificationExecutor<Material>{
    boolean existsMaterialByName(String name);
}
