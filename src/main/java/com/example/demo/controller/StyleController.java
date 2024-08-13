package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entity.Style;
import com.example.demo.service.StyleService;
import com.example.demo.util.error.IdInvalidException;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
public class StyleController {
    private final StyleService styleService;

    public StyleController(StyleService styleService) {
        this.styleService = styleService;
    }

    @PostMapping("/styles")
    public ResponseEntity<Style> createStyle(@Valid @RequestBody Style style) throws IdInvalidException {
        if (this.styleService.isExistsStyle(style.getName()))
            throw new IdInvalidException("The style name exists.");
        return ResponseEntity.status(HttpStatus.CREATED).body(this.styleService.createStyle(style));
    }

    @PutMapping("/styles")
    public ResponseEntity<Style> updateStyle(@Valid @RequestBody Style style) throws IdInvalidException {
        if (this.styleService.getStyleById(style.getId()) == null)
            throw new IdInvalidException("Style id = " + style.getId() + " doesn't exist.");
        if (this.styleService.isExistsStyle(style.getName()))
            throw new IdInvalidException("The style name exists.");
        return ResponseEntity.ok().body(this.styleService.updateStyle(style));
    }

    @GetMapping("styles/{id}")
    public ResponseEntity<Style> getStyle(@PathVariable("id") long id) throws IdInvalidException {
        if (this.styleService.getStyleById(id) == null)
            throw new IdInvalidException("Style id = " + id + " doesn't exist.");
        return ResponseEntity.ok().body(this.styleService.getStyleById(id));
    }

    @GetMapping("styles")
    public ResponseEntity<List<Style>> getAllStyle() {
        return ResponseEntity.ok().body(this.styleService.getAllStyle());
    }

    @DeleteMapping("styles/{id}")
    public ResponseEntity<Void> deleteStyle(@PathVariable("id") long id) throws IdInvalidException {
        if (this.styleService.getStyleById(id) == null)
            throw new IdInvalidException("Style id = " + id + " doesn't exist.");
        this.styleService.deleteStyle(id);
        return ResponseEntity.ok(null);
    }
}
