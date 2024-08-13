package com.example.demo.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

import com.example.demo.domain.entity.Cart;
import com.example.demo.domain.response.ResultPaginationDTO;
import com.example.demo.service.CartService;
import com.example.demo.util.error.IdInvalidException;
import com.turkraft.springfilter.boot.Filter;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
public class CartsController {
    private final CartService cartService;

    public CartsController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/carts")
    public ResponseEntity<Cart> createCart(@Valid @RequestBody Cart cart) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.cartService.createCart(cart));
    }

    @PutMapping("/carts")
    public ResponseEntity<Cart> updateCart(@Valid @RequestBody Cart cart) throws IdInvalidException {
        if (this.cartService.getCartById(cart.getId()) == null) {
            throw new IdInvalidException("Cart id = " + cart.getId() + " doesn't exist.");
        }
        return ResponseEntity.ok().body(this.cartService.updateCart(cart));
    }

    @GetMapping("/carts/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable("id") long id) throws IdInvalidException {
        Cart cart = this.cartService.getCartById(id);
        if (cart == null) {
            throw new IdInvalidException("Cart id = " + id + " doesn't exist.");
        }
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/carts")
    public ResponseEntity<ResultPaginationDTO> getAllCart(@RequestBody long id,
            @Filter Specification<Cart> specification, Pageable pageable) {
        return ResponseEntity.ok(this.cartService.getAllCart(id, specification, pageable));
    }

    @DeleteMapping("/carts/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable("id") long id) throws IdInvalidException {
        if (this.cartService.getCartById(id) == null) {
            throw new IdInvalidException("Cart id = " + id + " doesn't exist.");
        }
        this.cartService.deleteCart(id);
        return ResponseEntity.ok(null);
    }

}
