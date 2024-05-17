package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.payload.ResponseData;
import com.example.demo.service.imp.LoginServiceImp;
import com.example.demo.utils.JwtUtilsHelper;

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

     @Autowired
     JwtUtilsHelper jwtUtilsHelper;

     @PostMapping("/signin")
     public ResponseEntity<?> signin(@RequestParam String username, @RequestParam String password) {
          ResponseData responseData = new ResponseData();
          UserDTO temp = loginServiceImp.checkLogin(username, password);
          if (temp!=null) {
               String token = jwtUtilsHelper.generateToken(temp);
               responseData.setData(token);
          } else {
               responseData.setData(false);
               responseData.setSuccess(false);
          }

          return new ResponseEntity<>(responseData, HttpStatus.OK);
     }

}
