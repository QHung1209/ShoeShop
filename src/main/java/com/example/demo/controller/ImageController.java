package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entity.Image;
import com.example.demo.service.ImageService;
import com.example.demo.service.ProductService;
import com.example.demo.util.error.IdInvalidException;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
public class ImageController {
    private final ImageService imageService;
    private final ProductService productService;

    public ImageController(ImageService imageService, ProductService productService) {
        this.imageService = imageService;
        this.productService = productService;
    }

    @PostMapping("/images")
    public ResponseEntity<Image> createImage(@Valid @RequestBody Image image) throws IdInvalidException {
        if (this.productService.getProductById(image.getProduct().getId()) == null) {
            throw new IdInvalidException("Product id = " + image.getProduct().getId() + " doesn't exist.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(this.imageService.createImage(image));

    }

    @PutMapping("/images")
    public ResponseEntity<Image> updateImage(@Valid @RequestBody Image image) throws IdInvalidException {
        if (this.imageService.getImageById(image.getId()) == null) {
            throw new IdInvalidException("Image id = " + image.getId() + " doesn't exist.");
        }

        if (this.productService.getProductById(image.getProduct().getId()) == null) {
            throw new IdInvalidException("Product id = " + image.getProduct().getId() + " doesn't exist.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(this.imageService.updateImage(image));
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<Image> getImage(long id) throws IdInvalidException {
        Image image = this.imageService.getImageById(id);
        if (image == null) {
            throw new IdInvalidException("Image id = " + id + " doesn't exist.");
        }

        return ResponseEntity.ok().body(image);
    }

    @DeleteMapping("/images/{id}")
    public ResponseEntity<Void> deleteImage(long id) throws IdInvalidException {
        if (this.imageService.getImageById(id) == null) {
            throw new IdInvalidException("Image id = " + id + " doesn't exist.");
        }
        this.imageService.deleteImage(id);
        return ResponseEntity.ok(null);
    }
}
