package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Shoe;
import com.example.demo.domain.response.ResultPaginationDTO;
import com.example.demo.repository.ShoeRepository;

@Service
public class ShoeService {
    private final ShoeRepository shoeRepository;

    public ShoeService(ShoeRepository shoeRepository) {
        this.shoeRepository = shoeRepository;
    }

    public Shoe createShoe(Shoe shoe) {
        return this.shoeRepository.save(shoe);
    }

    public Shoe getShoeById(long id) {
        Optional<Shoe> shoeOptional = this.shoeRepository.findById(id);
        return shoeOptional.isPresent() ? shoeOptional.get() : null;
    }

    public boolean existsShoeByName(String name)
    {
        return this.shoeRepository.existsShoeByName(name);
    }

    public Shoe updateShoe(Shoe shoe) {
        Shoe update = this.getShoeById(shoe.getId());
        update.setName(shoe.getName());
        update.setPrice(shoe.getPrice());
        return this.shoeRepository.save(update);
    }

    public ResultPaginationDTO getAllShoe(Specification<Shoe> specification, Pageable pageable) {
        Page<Shoe> pageShoe = this.shoeRepository.findAll(specification, pageable);
        ResultPaginationDTO rs = new ResultPaginationDTO();
        ResultPaginationDTO.Meta mt = new ResultPaginationDTO.Meta();

        mt.setPage(pageable.getPageNumber() + 1);
        mt.setPageSize(pageable.getPageSize());

        mt.setPages(pageShoe.getTotalPages());
        mt.setTotal(pageShoe.getTotalPages());

        rs.setMeta(mt);
        
        rs.setResult(pageShoe.getContent());
        return rs;
    }


}
