package com.example.demo.repository;


import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByUsernameAndPassword(String username, String password);
    Users findByUsername(String username);
    
    @Modifying
    @Transactional
    @Query("UPDATE Users s Set s.name = :name, s.telephone = :telephone, s.password = :password, s.address = :address WHERE s.user_id =:user_id")
    void updateUser(String name, String telephone, String password, String address, int user_id);

}
