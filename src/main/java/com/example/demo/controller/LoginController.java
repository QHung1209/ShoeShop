package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payload.ResponseData;
import com.example.demo.service.imp.LoginServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

@CrossOrigin("*")
@RestController
@RequestMapping("/login")

public class LoginController {

    @Autowired
    LoginServiceImp loginServiceImp;

   @PostMapping("/signin")
   public ResponseEntity<?> signin(@RequestParam String username, @RequestParam String password) {
       ResponseData responseData = new ResponseData();
        if(loginServiceImp.checkLogin(username, password))
       {
            responseData.setData(true);
       }
       else
            responseData.setData(false);
       
       return new ResponseEntity<>(responseData, HttpStatus.OK);
   }

}
