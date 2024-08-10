package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Cart;
import com.example.demo.repository.CartRepository;
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;

    public CartService(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    public List<Cart> getAllCart(Long id) {
        List<Cart> listData = cartRepository.findByUserId(id);

        // for (Cart ca : listData) {
        // // Kiểm tra sản phẩm đó trong kho còn không, nếu không còn thì không hiện lên
        // // giao diện
        // if (inventoryReposity.Quantity(ca.getProducts().getProduct_id(),
        // ca.getSizes().getSize_id()) == 0)
        // continue;
        // ProductDTO proTemp = new ProductDTO();
        // proTemp.setProduct_id(ca.getProducts().getProduct_id());
        // proTemp.setColor_name(ca.getProducts().getColors().getColor_name());
        // proTemp.setDiscount(ca.getProducts().getDiscount());
        // proTemp.setImage_url(ca.getProducts().getImage_url());
        // proTemp.setShoe_name(ca.getProducts().getShoes().getName());
        // proTemp.setPrice(ca.getProducts().getShoes().getPrice());

        // CartDTO temp = new CartDTO();
        // temp.setCart_id(ca.getCart_id());
        // temp.setUser_id(ca.getUsers().getUser_id());
        // temp.setProductDTO(proTemp);
        // temp.setSize_id(ca.getSizes().getSize_id());
        // temp.setSize_name(ca.getSizes().getSize_name());
        // temp.setQuantity(ca.getQuantity());
        // listCartDTOs.add(temp);
        // }
        // return listCartDTOs;
        return listData;
    }

    public Cart insertCart(Cart cart) {
        Cart existsCart = this.cartRepository.findByUserIdAndProductIdAndSizeId(cart.getUser().getId(),
                cart.getProduct().getId(), cart.getSize().getId());

        if (existsCart != null) {
            // If the cart already exists, update the quantity
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
