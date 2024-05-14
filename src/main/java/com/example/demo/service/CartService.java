package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import com.example.demo.dto.CartDTO;
import com.example.demo.entity.Carts;
import com.example.demo.entity.Products;
import com.example.demo.entity.Sizes;
import com.example.demo.entity.Users;
import com.example.demo.repository.CartRepository;
import com.example.demo.service.imp.CartServiceImp;

@Service
public class CartService implements CartServiceImp {

    @Autowired
    CartRepository cartRepository;

    @Override
    public List<CartDTO> getAllCart() {
        List<CartDTO> listCartDTOs = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(0, Integer.MAX_VALUE);
        Page<Carts> listData = cartRepository.findAll(pageRequest);

        for (Carts ca : listData) {
            CartDTO temp = new CartDTO();
            temp.setCart_id(ca.getCart_id());
            temp.setProduct_id(ca.getProducts().getProduct_id());
            temp.setSize_id(ca.getSizes().getSize_id());
            temp.setSize_name(ca.getSizes().getSize_name());
            temp.setQuantity(ca.getQuantity());
            temp.setImage_url(ca.getProducts().getImage_url());
            temp.setShoe_name(ca.getProducts().getShoes().getName());
            temp.setPrice(ca.getProducts().getShoes().getPrice());
            listCartDTOs.add(temp);
        }
        return listCartDTOs;
    }

    @Override
    public boolean insertCart(int user_id, int product_id, int size_id, int quantity) {
        Carts existingCart = cartRepository.findCart(user_id, product_id, size_id, PageRequest.of(0, 1)).stream()
                .findFirst().orElse(null);

        if (existingCart != null) {
            // If the cart already exists, update the quantity
            existingCart.setQuantity(existingCart.getQuantity() + quantity);
            cartRepository.save(existingCart);
        } else {
            // If the cart does not exist, create a new cart entry
            Carts cart = new Carts();
            Users user = new Users();
            Products product = new Products();
            Sizes size = new Sizes();
            product.setProduct_id(product_id);
            size.setSize_id(size_id);
            user.setUser_id(user_id);
            cart.setProducts(product);
            cart.setUsers(user);
            cart.setSizes(size);
            cart.setQuantity(quantity);

            cartRepository.save(cart);
        }

        return true;
    }

    @Override
    public boolean deleteCart(int user_id, int product_id, int size_id) { 
        // Tìm và xóa giỏ hàng dựa trên user_id và product_id
        Carts carts = cartRepository.findCartByUserIdAndProductId(user_id, product_id, size_id);
        cartRepository.delete(carts);
        return true;
    }

}
