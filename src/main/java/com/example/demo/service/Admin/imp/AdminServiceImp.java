package com.example.demo.service.Admin.imp;

import java.util.List;
import com.example.demo.dto.Admin.AdminDTO;
import com.example.demo.entity.Admin.Admins;

public interface AdminServiceImp {
    List<AdminDTO> getAllUser();

    public void updateAdmin(int userId, String newUserName, String newPassword, String newName, String newAddress, String newTelephone);   

    // boolean deleteAdmin(Integer id);

    Admins addAdmin(Admins admin);

}
