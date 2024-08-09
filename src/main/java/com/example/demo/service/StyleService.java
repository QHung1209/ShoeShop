package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Product;
import com.example.demo.domain.entity.Style;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StyleRepository;

@Service
public class StyleService {
    private final StyleRepository styleRepository;
    private final ProductRepository productRepository;

    public StyleService(StyleRepository styleRepository, ProductRepository productRepository) {
        this.styleRepository = styleRepository;
        this.productRepository = productRepository;
    }

    public boolean isExistsStyle(String name) {
        return this.styleRepository.existsStyleByName(name);
    }

    public Style createStyle(Style style) {
        return this.styleRepository.save(style);
    }

    public Style getStyleById(Long id) {
        Optional<Style> styleOptional = this.styleRepository.findById(id);
        return styleOptional.isPresent() ? styleOptional.get() : null;
    }

    public Style updateStyle(Style style) {
        Style update = this.getStyleById(style.getId());
        update.setName(style.getName());

        update = this.styleRepository.save(update);
        return update;
    }

    public List<Style> getAllStyle() {
        return this.styleRepository.findAll();
    }

    public void deleteStyle(long id) {
        Style style = this.getStyleById(id);
        if (!style.getProducts().isEmpty()) {
            List<Product> listProducts = this.productRepository.findByStyle(style);
            this.productRepository.deleteAll(listProducts);
        }
        this.styleRepository.deleteById(id);
    }
}
