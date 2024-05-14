package com.example.demo.controller.Admin;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.service.Admin.imp.AdminServiceImp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;




@RestController
//Đăng ký đường dẫn
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminServiceImp adminServiceImp;


    @GetMapping("")
    public ResponseEntity<?> getAllUser() {
        return new ResponseEntity<>(adminServiceImp.getAllUser(), HttpStatus.OK);
    }
    

}
