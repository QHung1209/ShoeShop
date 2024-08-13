package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entity.Material;
import com.example.demo.service.MaterialService;
import com.example.demo.util.error.IdInvalidException;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
public class MaterialController {
    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @PostMapping("/materials")
    public ResponseEntity<Material> createMaterial(@Valid @RequestBody Material material) throws IdInvalidException {
        if (this.materialService.isExistsMaterial(material.getName()))
            throw new IdInvalidException("The material name exists.");
        return ResponseEntity.status(HttpStatus.CREATED).body(this.materialService.createMaterial(material));
    }

    @GetMapping("/materials/{id}")
    public ResponseEntity<Material> getMaterial(@PathVariable("id") long id) throws IdInvalidException {
        Material material = this.materialService.getMaterialById(id);
        if (material == null)
            throw new IdInvalidException("Material id = " + id + " doesn't exist.");
        if (this.materialService.isExistsMaterial(material.getName()))
            throw new IdInvalidException("The material name exists.");
        return ResponseEntity.ok(material);
    }

    @GetMapping("/materials")
    public ResponseEntity<List<Material>> getAllMaterial() {
        return ResponseEntity.ok(this.materialService.getAllMaterial());
    }
}
