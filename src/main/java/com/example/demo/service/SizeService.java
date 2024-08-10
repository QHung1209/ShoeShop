package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Product;
import com.example.demo.domain.entity.Size;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.SizeRepository;

@Service
public class SizeService {
    private final SizeRepository sizeRepository;
    private final ProductRepository productRepository;

    public SizeService(SizeRepository sizeRepository, ProductRepository productRepository) {
        this.sizeRepository = sizeRepository;
        this.productRepository = productRepository;
    }

    public Size createSize(Size size) {
        return this.sizeRepository.save(size);
    }

    public Size getSizeById(long id) {
        Optional<Size> size = this.sizeRepository.findById(id);
        return size.isPresent() ? size.get() : null;
    }

    public Size updateSize(Size size) {
        Size update = this.getSizeById(size.getId());
        update.setName(size.getName());
        update = this.sizeRepository.save(update);

        return update;
    }

    public List<Size> getAllSize() {
        return this.sizeRepository.findAll();
    }

    public void deleteSize(long id) {
        Size size = this.getSizeById(id);
        this.sizeRepository.deleteById(id);
    }
}
