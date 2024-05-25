package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Users;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.imp.UserServiceImp;
import com.example.demo.utils.JwtUtilsHelper;

@Service
public class UserService implements UserServiceImp {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtilsHelper jwtUtilsHelper;

    @Override
    public List<UserDTO> getAllUser() {
        List<Users> listUser = userRepository.findAll();
        List<UserDTO> lUserDTOs = new ArrayList<>();
        for (Users user : listUser) {
            UserDTO temp = new UserDTO();
            temp.setUser_id(user.getUser_id());
            temp.setUsername(user.getUsername());
            temp.setPassword(user.getPassword());
            temp.setAddress(user.getAddress());
            temp.setName(user.getname());
            temp.setTelephone(user.getTelephone());

            lUserDTOs.add(temp);
        }
        return lUserDTOs;
    }

    public static UserDTO getUserDTOFromOrder(Orders order) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUser_id(order.getUsers().getUser_id());
        userDTO.setAddress(order.getAddress());
        userDTO.setTelephone(order.getTelephone());
        userDTO.setName(order.getName());
        return userDTO;
    }

    @Override
    public UserDTO findUserByJwt(String jwt) {

        UserDTO temp = new UserDTO();

        return temp;
    }

    @Override
    public UserDTO findByUsernameAndPass(String username, String password) {
        Users user = userRepository.findByUsernameAndPassword(username, password);
        UserDTO temp = new UserDTO();
        temp.setUser_id(user.getUser_id());
        temp.setUsername(username);
        temp.setPassword(password);
        temp.setTelephone(user.getTelephone());
        temp.setAddress(user.getAddress());
        temp.setName(user.getName());
        return temp;
    }

}
