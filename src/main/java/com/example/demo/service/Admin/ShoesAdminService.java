package com.example.demo.service.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Shoes;
import com.example.demo.repository.Admin.ShoesAdminRepository;
import com.example.demo.service.Admin.imp.ShoesAdminServiceImp;
import com.example.demo.dto.Admin.ShoesDTO;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.Page;

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
            shoesAdminRepository.save(shoes);
            isSuccess = true;

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error insert shoes" + e.getMessage());
        }

        return isSuccess;
    }

    @Override
    //Liệt kê danh sách giày
    public List<ShoesDTO> getShoes() {
        // TODO Auto-generated method stub
        List<ShoesDTO> listShoesDTO = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(0, 50);
        Page<Shoes> listData = shoesAdminRepository.findAll(pageRequest);

        for(Shoes data: listData ){
            ShoesDTO shoesDTO = new ShoesDTO();
            shoesDTO.setName(data.getName());
            shoesDTO.setPrice(data.getPrice());
            listShoesDTO.add(shoesDTO);
        }
        return listShoesDTO;
    }
}
