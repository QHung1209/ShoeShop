package com.example.demo.service.Admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.service.Admin.imp.SizesAdminServiceImp;
import com.example.demo.dto.Admin.SizesDTO;
import com.example.demo.entity.Sizes;
import com.example.demo.repository.Admin.SizesAdminRepository;

@Service
public class SizesAdminService implements SizesAdminServiceImp {

    @Autowired
    SizesAdminRepository sizesAdminRepository;

    @Override
    public boolean insertSizes(int size_name) {
        boolean isSuccess = false;
        try {
            Sizes sizes = new Sizes();
            sizes.setSize_name(size_name);
            sizesAdminRepository.saveSize(sizes);
            isSuccess = true;

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error insert shoes" + e.getMessage());
        }

        return isSuccess;
    }

    @Override
    public List<SizesDTO> getSizes() {
        List<SizesDTO> listSizesDTO = new ArrayList<>();

        List<Sizes> listData = sizesAdminRepository.findAll();

        for (Sizes data : listData) {
            SizesDTO sizesDTO = new SizesDTO();
            sizesDTO.setSize_name(data.getSize_name());
            listSizesDTO.add(sizesDTO);
        }
        return listSizesDTO;
    }

    @Override
    public ResponseEntity<Void> deleteSizeByName(int size_name) {
        Sizes sizes = sizesAdminRepository.findBySizeName(size_name);
        if (sizes != null) {
            sizesAdminRepository.delete(sizes);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
