package com.example.demo.service.Admin;

import com.example.demo.dto.Admin.AdminDTO;
import com.example.demo.repository.Admin.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.demo.entity.Admin.Admins;
import java.util.ArrayList;
import com.example.demo.service.Admin.imp.LoginAdminServiceImp;
import com.example.demo.payload.Admin.SignUpAdminRequest;
import com.example.demo.entity.Roles;

@Service
public class LoginAdminService implements LoginAdminServiceImp {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public List<AdminDTO> getAllAdmins() {
        List<Admins> listAdmin = adminRepository.findAll();
        List<AdminDTO> lAdminDTOs = new ArrayList<>();

        for (Admins admin : listAdmin) {
            AdminDTO adminDTO = new AdminDTO();
            adminDTO.setId(admin.getId());
            adminDTO.setUsername(admin.getUsername());
            adminDTO.setName(admin.getName());
            adminDTO.setTelephone(admin.getTelephone());
            adminDTO.setAddress(admin.getAddress());
            lAdminDTOs.add(adminDTO);
        }
        return lAdminDTOs;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        List<Admins> listAdmin = adminRepository.findByUsernameAndPassword(username, password);
        if (listAdmin.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addUser(SignUpAdminRequest signUpAdminRequest) {
        Admins admins = new Admins();

        Roles roles = new Roles();
        roles.setRole_id(signUpAdminRequest.getRole_id());
        admins.setName(signUpAdminRequest.getName());
        admins.setUsername(signUpAdminRequest.getUsername());
        admins.setPassword(signUpAdminRequest.getPassword());
        admins.setAddress(signUpAdminRequest.getAddress());
        admins.setTelephone(signUpAdminRequest.getTelephone());
        admins.setRoles(roles);
        try{
            admins = adminRepository.save(admins);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
