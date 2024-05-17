package com.example.demo.repository.Admin;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Shoes;
import java.util.Optional;

@Repository
public interface ShoesAdminRepository extends JpaRepository<Shoes, Integer>{
    Optional<Shoes> findByName(String name);
}


