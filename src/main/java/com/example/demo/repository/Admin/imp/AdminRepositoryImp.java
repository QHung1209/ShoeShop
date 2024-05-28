package com.example.demo.repository.Admin.imp;

import com.example.demo.entity.Admin.Admins;

import java.util.List;

public interface AdminRepositoryImp {

    public Admins saveAdmin(Admins admin);

    public Admins findByAdminName(String name);

    public List<Admins> findAll();

    public void delete(Admins admin);

    public void updateAdmin(int userId, String newUserName, String newPassword, String newName, String newAddress, String newTelephone);

    public boolean isNameExisted(String name); 
    
    public List<Admins> findByUsernameAndPassword(String username, String password);
}
