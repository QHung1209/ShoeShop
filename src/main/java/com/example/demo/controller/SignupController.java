package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.payload.ResponseData;
import com.example.demo.service.imp.UserServiceImp;
import org.springframework.web.bind.annotation.PostMapping;

@CrossOrigin("*")
@RestController
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    UserServiceImp userServiceImp;

    @PostMapping("/")
    public ResponseEntity<?> insertUser(@RequestParam String username,
            @RequestParam String password,
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam String telephone) {
        ResponseData responseData = new ResponseData();
        responseData.setData(userServiceImp.insertUser(username, password, name, address, telephone));

        return new ResponseEntity<>(responseData, HttpStatus.OK);

    }

}
