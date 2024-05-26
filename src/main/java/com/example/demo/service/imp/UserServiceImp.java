package com.example.demo.service.imp;
import java.util.List;

import com.example.demo.dto.UserDTO;
public interface UserServiceImp {

    List<UserDTO> getAllUser();
    public UserDTO findUserByJwt(String jwt);

    UserDTO findByUsernameAndPass(String username, String password);

    boolean insertUser(String username, String password, String name, String address, String telephone);
}
