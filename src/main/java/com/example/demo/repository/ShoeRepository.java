package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.Shoe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface ShoeRepository extends JpaRepository<Shoe, Long> , JpaSpecificationExecutor<Shoe> {
    boolean existsShoeByName(String name);
}
