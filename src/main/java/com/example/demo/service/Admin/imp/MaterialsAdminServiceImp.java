package com.example.demo.service.Admin.imp;

import java.util.List;
import com.example.demo.dto.Admin.MaterialsDTO;
import org.springframework.http.ResponseEntity;

public interface MaterialsAdminServiceImp {
    boolean insertMaterials(String material_name);

    List<MaterialsDTO> getMaterials();

    ResponseEntity<Void> deleteMaterialByName(String material_name);

    ResponseEntity<Void> updateMaterialName(String material_name, String newMaterialName);

    boolean checkMaterialExists(String material_name);

    List<String> getAllMaterialName();


}
