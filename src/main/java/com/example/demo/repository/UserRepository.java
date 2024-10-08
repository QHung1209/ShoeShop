package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor<User>{
    User findByEmail(String email);
    User findByName(String username);
    User findByRefreshTokenAndEmail(String token, String email);
    boolean existsByEmail(String email);
} 