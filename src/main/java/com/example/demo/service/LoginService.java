package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Users;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.imp.LoginServiceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginServiceImp {

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
    public UserDTO checkLogin(String username, String password) {
        Users listUser = userRepository.findByUsernameAndPassword(username, password);
        UserDTO temp = new UserDTO();
        temp.setUser_id(listUser.getUser_id());
        temp.setUsername(username);
        temp.setTelephone(listUser.getTelephone());
        temp.setName(listUser.getName());
        temp.setAddress(listUser.getAddress());
        temp.setPassword(password);
        return temp;
    }

}
