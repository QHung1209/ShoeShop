package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.CartDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Carts;
import com.example.demo.entity.Products;
import com.example.demo.entity.Sizes;
import com.example.demo.entity.Users;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.InventoryReposity;
import com.example.demo.service.imp.CartServiceImp;

@Service
public class CartService implements CartServiceImp {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    InventoryReposity inventoryReposity;

    @Override
    public List<CartDTO> getAllCart(int user_id) {
        List<CartDTO> listCartDTOs = new ArrayList<>();
        List<Carts> listData = cartRepository.findAllCartById(user_id);

        for (Carts ca : listData) {
            // Kiểm tra sản phẩm đó trong kho còn không, nếu không còn thì không hiện lên
            // giao diện
            if (inventoryReposity.Quantity(ca.getProducts().getProduct_id(), ca.getSizes().getSize_id()) == 0)
                continue;
            ProductDTO proTemp = new ProductDTO();
            proTemp.setProduct_id(ca.getProducts().getProduct_id());
            proTemp.setColor_name(ca.getProducts().getColors().getColor_name());
            proTemp.setDiscount(ca.getProducts().getDiscount());
            proTemp.setImage_url(ca.getProducts().getImage_url());
            proTemp.setShoe_name(ca.getProducts().getShoes().getName());
            proTemp.setPrice(ca.getProducts().getShoes().getPrice());

            CartDTO temp = new CartDTO();
            temp.setCart_id(ca.getCart_id());
            temp.setUser_id(ca.getUsers().getUser_id());
            temp.setProductDTO(proTemp);
            temp.setSize_id(ca.getSizes().getSize_id());
            temp.setSize_name(ca.getSizes().getSize_name());
            temp.setQuantity(ca.getQuantity());
            listCartDTOs.add(temp);
        }
        return listCartDTOs;
    }

    @Override
    public boolean insertCart(int user_id, int product_id, int size_id, int quantity) {
        Carts existingCart = cartRepository.findCart(user_id, product_id, size_id);

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

    @Override
    public boolean updateCart(int user_id, int cart_id, int product_id, int size_id, int quantity) {
        Carts existingCart = cartRepository.findCartByUserIdAndProductId(user_id, product_id, size_id);
        if (existingCart != null && existingCart.getCart_id() != cart_id) {
            existingCart.setQuantity(quantity);
            cartRepository.save(existingCart);
            cartRepository.deleteAllCartByCartId(cart_id);
        } else if(existingCart != null && existingCart.getCart_id() == cart_id || existingCart == null) {
            cartRepository.updateCartBy(cart_id, size_id, quantity);
        } 
        return true;
    }

}
