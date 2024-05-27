package com.example.demo.repository.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Admin.Admin;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{
    //Select * admins where username = ? and password = ?
    List<Admin> findByUsernameAndPassword(String username, String password);
}
