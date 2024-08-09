package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payload.ResponseData;
import com.example.demo.service.imp.UserServiceImp;
import com.example.demo.util.JwtUtilsHelper;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    JwtUtilsHelper jwtUtilsHelper;

    @GetMapping("/getall")
    public ResponseEntity<?> getAllUser() {
        return new ResponseEntity<>(userServiceImp.getAllUser(), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestParam String name,
            @RequestParam String telephone,
            @RequestParam String password,
            @RequestParam String address,
            @RequestParam int user_id) {
        ResponseData responseData = new ResponseData();
        responseData.setData(userServiceImp.updateUser(name, telephone, password, address, user_id));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/Detail")
    public ResponseEntity<?> getUser(@RequestHeader("Authorization") String jwt) {

        ResponseData responseData = new ResponseData();
        //responseData.setData(jwtUtilsHelper.getIdFromJwtToken(jwt));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

}
