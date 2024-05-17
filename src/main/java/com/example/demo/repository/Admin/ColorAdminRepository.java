package com.example.demo.repository.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Colors;

@Repository
public interface ColorAdminRepository extends JpaRepository<Colors, Integer>{

}
