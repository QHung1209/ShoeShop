package com.example.demo.service.Admin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.dto.Admin.MaterialsDTO;
import com.example.demo.entity.Materials;
import com.example.demo.repository.Admin.MaterialsAdminRepository;
import com.example.demo.service.Admin.imp.MaterialsAdminServiceImp;

@Service
public class MaterialsAdminService implements MaterialsAdminServiceImp {

    @Autowired
    MaterialsAdminRepository materialsAdminRepository;

    @Override
    public boolean insertMaterials(String material_name) {
        boolean isSuccess = false;
        try {

            Materials materials = new Materials();
            materials.setMaterial_name(material_name);
            materialsAdminRepository.saveMaterial(materials);
            isSuccess = true;

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error insert shoes" + e.getMessage());
        }

        return isSuccess;
    }

    @Override
    public List<MaterialsDTO> getMaterials() {
        // TODO Auto-generated method stub
        List<MaterialsDTO> listMaterialsDTO = new ArrayList<>();
        List<Materials> listData = materialsAdminRepository.findAll();

        for (Materials data : listData) {
            MaterialsDTO MaterialsDTO = new MaterialsDTO();
            MaterialsDTO.setMaterial_name(data.getMaterial_name());
            listMaterialsDTO.add(MaterialsDTO);
        }
        return listMaterialsDTO;
    }

    @Override
    public ResponseEntity<Void> deleteMaterialByName(String material_name) {
        Materials materials = materialsAdminRepository.findByMaterialName(material_name);
        if (materials != null) {
            materialsAdminRepository.delete(materials);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Void> updateMaterialName(String material_name, String newMaterialName) {
        Materials materials = materialsAdminRepository.findByMaterialName(material_name);
        if (materials != null) {
            materialsAdminRepository.updateMaterialName(material_name, newMaterialName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public boolean checkMaterialExists(String material_name) {
        return materialsAdminRepository.isMaterialNameExisted(material_name);
    }

    @Override
    public List<String> getAllMaterialName() {
        List<Materials> materialsList = materialsAdminRepository.findAll();
        return materialsList.stream()
                .map(Materials::getMaterial_name)
                .collect(Collectors.toList());
    }
}
