package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.Size;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long>, JpaSpecificationExecutor<Size> {
    boolean existsByName(String name);
}
