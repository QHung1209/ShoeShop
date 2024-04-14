package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Users;
import com.example.demo.repository.UserInterface;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/login")

public class LoginController {

    @Autowired
   // @Qualifier("")
    UserInterface userInterface;

    @PostMapping("/signin")
    public ResponseEntity<?> signin()
    {
        List<Users> listUser = userInterface.findAll();
        List<UserDTO> lUserDTOs = new ArrayList<>();
        for(Users user: listUser)
        {
            UserDTO temp = new UserDTO();
            temp.setUser_id(user.getUser_id());
            temp.setUsername(user.getUsername());
            temp.setPassword(user.getPassword());
            temp.setAddress(user.getAddress());
            temp.setName(user.getname());
            lUserDTOs.add(temp);
        }
        return new ResponseEntity<>(lUserDTOs, HttpStatus.OK);
    }
    
}
