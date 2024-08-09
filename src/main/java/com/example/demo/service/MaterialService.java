package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Material;
import com.example.demo.domain.entity.Product;
import com.example.demo.repository.MaterialRepository;
import com.example.demo.repository.ProductRepository;

@Service
public class MaterialService {
    private final MaterialRepository materialRepository;
    private final ProductRepository productRepository;

    public MaterialService(MaterialRepository materialRepository, ProductRepository productRepository) {
        this.materialRepository = materialRepository;
        this.productRepository = productRepository;
    }

    public boolean isExistsMaterial(String name) {
        return this.materialRepository.existsMaterialByName(name);
    }

    public Material createMaterial(Material material) {
        return this.materialRepository.save(material);
    }

    public Material getMaterialById(long id) {
        Optional<Material> materialOptional = this.materialRepository.findById(id);

        return materialOptional.isPresent() ? materialOptional.get() : null;
    }

    public Material updateMaterial(Material material) {
        Material update = this.getMaterialById(material.getId());

        update.setName(material.getName());

        update = this.materialRepository.save(update);
        return update;
    }

    public void deleteMaterial(long id) {
        Material update = this.getMaterialById(id);
        if (!update.getProducts().isEmpty()) {
            List<Product> listProducts = this.productRepository.findByMaterial(update);
            this.productRepository.deleteAll(listProducts);
        }
        this.materialRepository.deleteById(id);
    }

    public List<Material> getAllMaterial() {
        return this.materialRepository.findAll();
    }
}
