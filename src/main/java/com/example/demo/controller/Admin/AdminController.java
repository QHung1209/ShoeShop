package com.example.demo.controller.Admin;

import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.Admin.imp.AdminServiceImp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.example.demo.entity.Admin.Admins;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.Collections;

@CrossOrigin("*")

@RestController
// Đăng ký đường dẫn
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminServiceImp adminServiceImp;

    @GetMapping("")
    public ResponseEntity<?> getAllUser() {
        return new ResponseEntity<>(adminServiceImp.getAllUser(), HttpStatus.OK);
    }

    @PostMapping("/addAdmin")
    public ResponseEntity<?> createUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("address") String address,
            @RequestParam("telephone") String telephone

    ) {
        Admins admin = new Admins();
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setName(name);
        admin.setAddress(address);
        admin.setTelephone(telephone);

        Admins newAdmin = adminServiceImp.addAdmin(admin);
        return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
    }

    @PutMapping("/updateAdmin/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Integer id,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("address") String address,
            @RequestParam("telephone") String telephone

    ) {
        // Update the admin
        adminServiceImp.updateAdmin(id, username, password, name, address, telephone);
        return new ResponseEntity<>(Collections.singletonMap("message", "User updated successfully"), HttpStatus.OK);
    }

    // @DeleteMapping("deleteAdmin/{id}")
    // public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
    // boolean isDeleted = adminServiceImp.deleteAdmin(id);
    // if (!isDeleted) {
    // return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    // }
    // return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    // }

}
