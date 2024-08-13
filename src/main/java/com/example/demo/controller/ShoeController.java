package com.example.demo.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entity.Shoe;
import com.example.demo.domain.response.ResultPaginationDTO;
import com.example.demo.service.ShoeService;
import com.example.demo.util.error.IdInvalidException;
import com.turkraft.springfilter.boot.Filter;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
public class ShoeController {
    private final ShoeService shoeService;

    public ShoeController(ShoeService shoeService) {
        this.shoeService = shoeService;
    }

    @PostMapping("/shoes")
    public ResponseEntity<Shoe> createShoe(@Valid @RequestBody Shoe shoe) throws IdInvalidException {
        if (this.shoeService.existsShoeByName(shoe.getName()))
            throw new IdInvalidException("The shoe name exists.");
        return ResponseEntity.status(HttpStatus.CREATED).body(this.shoeService.createShoe(shoe));
    }

    @GetMapping("/shoes/{id}")
    public ResponseEntity<Shoe> getShoe(@PathVariable("id") long id) throws IdInvalidException {
        Shoe shoe = this.shoeService.getShoeById(id);
        if (shoe == null)
            throw new IdInvalidException("Shoe id = " + id + " doesn't exist.");
        return ResponseEntity.ok(shoe);
    }

    @GetMapping("/shoes")
    public ResponseEntity<ResultPaginationDTO> getAllShoe(@Filter Specification<Shoe> specification,
            Pageable pageable) {
        return ResponseEntity.ok(this.shoeService.getAllShoe(specification, pageable));
    }

    @PutMapping("/shoes")
    public ResponseEntity<Shoe> updateShoe(@Valid @RequestBody Shoe shoe) throws IdInvalidException {
        if (this.shoeService.getShoeById(shoe.getId()) == null)
            throw new IdInvalidException("Shoe id = " + shoe.getId() + " doesn't exist.");
        if (this.shoeService.existsShoeByName(shoe.getName()))
            throw new IdInvalidException("The shoe name exists.");
        return ResponseEntity.ok(this.shoeService.updateShoe(shoe));
    }
}
