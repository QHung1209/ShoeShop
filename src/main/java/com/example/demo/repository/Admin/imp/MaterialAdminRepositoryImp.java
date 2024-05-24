package com.example.demo.repository.Admin.imp;

import com.example.demo.entity.Materials;
import java.util.List;

public interface MaterialAdminRepositoryImp {

    Materials saveMaterial(Materials material);

    List<Materials> findAll();

    Materials findByMaterialName(String material_name);

    void delete(Materials material);

    void updateMaterialName(String material_name, String newMaterialName);

    boolean isMaterialNameExisted(String material_name);
}
