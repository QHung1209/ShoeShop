package com.example.demo.repository.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Admin.Admins;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admins, Integer>{
    //Select * admins where username = ? and password = ?
    List<Admins> findByUsernameAndPassword(String username, String password);
}
