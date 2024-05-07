package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Users;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.imp.LoginServiceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginServiceImp{

    @Autowired
    UserRepository userRepository;
    @Override
    public List<UserDTO> getAllUsers() {
        List<Users> listUser = userRepository.findAll();
        List<UserDTO> lUserDTOs = new ArrayList<>();
        for (Users user : listUser) {
            UserDTO temp = new UserDTO();
            temp.setUser_id(user.getUser_id());
            temp.setUsername(user.getUsername());
            temp.setPassword(user.getPassword());
            temp.setAddress(user.getAddress());
            temp.setName(user.getname());
            lUserDTOs.add(temp);
        }
        return lUserDTOs;
    }

    @Override
    public boolean checkLogin(String username, String password) {
       List<Users> listUser = userRepository.findByUsernameAndPassword(username, password);
        
       return listUser.size() > 0;
    }


}
