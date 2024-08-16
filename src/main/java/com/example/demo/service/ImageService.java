package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Image;
import com.example.demo.repository.ImageRepository;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image createImage(Image image) {
        return this.imageRepository.save(image);
    }

    public Image getImageById(long id) {
        Optional<Image> image = this.imageRepository.findById(id);
        return image.isPresent() ? image.get() : null;
    }

    public Image updateImage(Image image) {
        Image update = this.getImageById(image.getId());
        image.setUrl(image.getUrl());
        if (image.getProduct() != null) {
            update.setProduct(image.getProduct());
        }

        update = this.imageRepository.save(update);
        return update;
    }
    
    public void deleteImage(long id)
    {
        this.imageRepository.deleteById(id);
    }
}
