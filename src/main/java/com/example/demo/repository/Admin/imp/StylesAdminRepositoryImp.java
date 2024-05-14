package com.example.demo.repository.Admin.imp;

import java.util.List;

import com.example.demo.entity.Styles;

public interface StylesAdminRepositoryImp {

    List<Styles> findAll();

    Styles findByStyleName(String style_name);

    Styles saveStyle(Styles style);

    void delete(Styles style);

    void updateStyleName(String style_name, String newStyleName);
}
