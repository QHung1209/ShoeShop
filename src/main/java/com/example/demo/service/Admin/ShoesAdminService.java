package com.example.demo.service.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Shoes;
import com.example.demo.repository.Admin.ShoesAdminRepository;
import com.example.demo.service.Admin.imp.ShoesAdminServiceImp;
import com.example.demo.dto.Admin.ShoesDTO;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Service
public class ShoesAdminService implements ShoesAdminServiceImp {

    @Autowired
    ShoesAdminRepository shoesAdminRepository;

    @Override
    public boolean insertShoes(String name, int price) {
        boolean isSuccess = false;
        try {

            Shoes shoes = new Shoes();
            shoes.setName(name);
            shoes.setPrice(price);
            shoesAdminRepository.saveShoes(shoes);
            isSuccess = true;

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error insert shoes" + e.getMessage());
        }

        return isSuccess;
    }

    // Lấy dữ liệu từ shoes table
    @Override
    public List<ShoesDTO> getShoes() {
        // TODO Auto-generated method stub
        List<ShoesDTO> listShoesDTO = new ArrayList<>();

        List<Shoes> listData = shoesAdminRepository.findAll();

        for (Shoes data : listData) {
            ShoesDTO shoesDTO = new ShoesDTO();
            shoesDTO.setName(data.getName());
            shoesDTO.setPrice(data.getPrice());
            listShoesDTO.add(shoesDTO);
        }
        return listShoesDTO;
    }

    // Update shoes name
    @Override
    public ResponseEntity<Void> updateShoesName(String name, String newName) {
        Shoes shoes = shoesAdminRepository.findByShoesName(name);
        if (shoes != null) {
            shoesAdminRepository.updateShoesName(name, newName);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update shoes price
    @Override
    public ResponseEntity<Void> updateShoesPrice(String name, int newPrice) {
        Shoes shoes = shoesAdminRepository.findByShoesName(name);
        if (shoes != null) {
            shoesAdminRepository.updateShoesPrice(name, newPrice);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Kiểm tra name có tồn tại không
    @Override
    public boolean checkShoesNameExists(String name) {
        // TODO Auto-generated method stub
        return shoesAdminRepository.isShoesNameExisted(name);
    }

    // Lấy tên cả giày có trong bảng shoes
    @Override
    public List<String> getAllShoesName() {
        List<Shoes> shoesList = shoesAdminRepository.findAll();
        return shoesList.stream()
                .map(Shoes::getName)
                .collect(Collectors.toList());
    }
}
