package com.example.demo.service.Admin.imp;
import com.example.demo.dto.Admin.AdminDTO;
import java.util.List;
import com.example.demo.payload.Admin.SignUpAdminRequest;
public interface LoginAdminServiceImp {
    List<AdminDTO> getAllAdmins();
    boolean checkLogin(String username, String password);
    boolean addUser(SignUpAdminRequest signUpAdminRequest);
}
