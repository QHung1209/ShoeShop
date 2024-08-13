package com.example.demo.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Cart;
import com.example.demo.domain.response.ResultPaginationDTO;
import com.example.demo.repository.CartRepository;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;

    public CartService(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    public Cart getCartById(long id) {
        Optional<Cart> cartOptional = this.cartRepository.findById(id);
        return cartOptional.isPresent() ? cartOptional.get() : null;
    }

    public ResultPaginationDTO getAllCart(Long id, Specification<Cart> specification, Pageable pageable) {
        ResultPaginationDTO rs = new ResultPaginationDTO();
        Page<Cart> pageCart = this.cartRepository.findByUserId(id,pageable);

        ResultPaginationDTO.Meta mt = new ResultPaginationDTO.Meta();

        mt.setPage(pageable.getPageNumber() + 1);
        mt.setPageSize(pageable.getPageSize());

        mt.setPages(pageCart.getTotalPages());
        mt.setTotal(pageCart.getTotalPages());

        rs.setMeta(mt);
        rs.setResult(pageCart.getContent());
        return rs;
    }

    public Cart createCart(Cart cart) {
        Cart existsCart = this.cartRepository.findByUserIdAndProductIdAndSizeId(cart.getUser().getId(),
                cart.getProduct().getId(), cart.getSize().getId());

        if (existsCart != null) {
            existsCart.setQuantity(existsCart.getQuantity() + cart.getQuantity());
            return this.cartRepository.save(existsCart);
        }

        return this.cartRepository.save(cart);
    }

    public void deleteCart(long id) {

        this.cartRepository.deleteById(id);

    }

    public Cart updateCart(Cart cart) {
        Cart existsCart = this.cartRepository.findByUserIdAndProductIdAndSizeId(cart.getUser().getId(),
                cart.getProduct().getId(), cart.getSize().getId());

        existsCart.setQuantity(cart.getQuantity());
        existsCart.setSize(cart.getSize());
        return this.cartRepository.save(existsCart);

    }

}
