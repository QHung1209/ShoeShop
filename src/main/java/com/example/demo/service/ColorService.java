package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Color;
import com.example.demo.domain.entity.Product;
import com.example.demo.repository.ColorRepository;
import com.example.demo.repository.ProductRepository;

@Service
public class ColorService {
    private final ColorRepository colorRepository;
    private final ProductRepository productRepository;

    public ColorService(ColorRepository colorRepository, ProductRepository productRepository) {
        this.colorRepository = colorRepository;
        this.productRepository = productRepository;
    }

    public boolean isExistsColor(String name) {
        return this.colorRepository.existsColorByName(name);
    }

    public Color getColorById(long id) {
        Optional<Color> colorOptional = this.colorRepository.findById(id);
        return colorOptional.isPresent() ? colorOptional.get() : null;
    }

    public Color createColor(Color color) {
        return this.colorRepository.save(color);
    }

    public Color updateColor(Color color) {
        Color update = this.getColorById(color.getId());
        update.setName(color.getName());
        update = this.colorRepository.save(update);
        return update;
    }

    public List<Color> getAllColor() {
        List<Color> listColors = this.colorRepository.findAll();
        return listColors;
    }

    public void deleteColor(long id) {
        Color color = this.getColorById(id);
        if (!color.getProducts().isEmpty()) {
            List<Product> listProducts = this.productRepository.findByColor(color);
            this.productRepository.deleteAll(listProducts);
        }
        this.colorRepository.deleteById(id);
    }
}
