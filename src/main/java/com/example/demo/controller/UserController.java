package com.example.demo.controller;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.user;
import com.example.demo.payload.ResponseData;
import com.example.demo.service.imp.UserServiceImp;
import com.example.demo.utils.JwtUtilsHelper;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<?> getAllUser()
    {

        return new ResponseEntity<>(userServiceImp.getAllUser(),HttpStatus.OK);
    }

    @GetMapping("/add")
    public String add() {
        return "added";
    }

    @GetMapping("/getDetail/{id}/{username}")
    public String getDetail(@PathVariable("id") int id, @PathVariable() String username) {
        return "Hello " + id + " " + username;
    }

    @PostMapping("/update")
    public String updateUser(@RequestBody List<user> user) {
        //TODO: process POST request
        for (user i : user)
        {
            System.out.println("hello " + i.getUsername() + " " + i.getId());
        }
        return "" ;
     }
     @GetMapping("/getId")
    public ResponseEntity<?>  getUser(@RequestHeader("Authorization") String jwt) {

        ResponseData responseData = new ResponseData();
        responseData.setData(jwtUtilsHelper.getIdFromJwtToken(jwt));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    
}
