package com.example.demo.repository.Admin.imp;

import com.example.demo.entity.Sizes;
import java.util.List;

public interface SizesAdminRepositoryImp {
    List<Sizes> findAll();

    Sizes findBySizeName(int size_name);

    Sizes saveSize(Sizes sizes);

    void updateSizeName(int size_name, int newSizeName);

    void delete(Sizes size);
}
