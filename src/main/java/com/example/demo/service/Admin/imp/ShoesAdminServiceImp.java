package com.example.demo.service.Admin.imp;

import com.example.demo.dto.Admin.ShoesDTO;
import java.util.List;

public interface ShoesAdminServiceImp {
    boolean insertShoes(String name, int price);
    List<ShoesDTO> getShoes();
}
