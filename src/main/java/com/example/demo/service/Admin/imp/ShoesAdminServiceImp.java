package com.example.demo.service.Admin.imp;

import com.example.demo.dto.Admin.ShoesDTO;
import java.util.List;

import org.springframework.http.ResponseEntity;

public interface ShoesAdminServiceImp {

    boolean insertShoes(String name, int price);

    List<ShoesDTO> getShoes();

    ResponseEntity<Void> updateShoesName(String name, String newName);

    ResponseEntity<Void> updateShoesPrice(String name, int newPrice);

    boolean checkShoesNameExists(String name);

    List<String> getAllShoesName();

}
