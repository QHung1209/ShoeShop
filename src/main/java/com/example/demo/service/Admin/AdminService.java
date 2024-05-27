package com.example.demo.service.Admin;

import com.example.demo.dto.Admin.AdminDTO;
import com.example.demo.entity.Admin.Admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.service.Admin.imp.AdminServiceImp;
import com.example.demo.repository.Admin.AdminRepository;

@Service
public class AdminService implements AdminServiceImp {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public List<AdminDTO> getAllUser() {
        List<Admin> listAdmin = adminRepository.findAll();
        List<AdminDTO> lAdminDTOs = new ArrayList<>();

        for (Admin admin : listAdmin) {
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
}
