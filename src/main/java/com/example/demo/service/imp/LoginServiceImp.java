package com.example.demo.service.imp;

import java.util.List;

import com.example.demo.dto.UserDTO;

public interface LoginServiceImp {
    List<UserDTO> getAllUsers();
    boolean checkLogin(String username, String password);
}
