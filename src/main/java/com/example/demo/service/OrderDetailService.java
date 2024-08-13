package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Cart;
import com.example.demo.domain.entity.Inventory;
import com.example.demo.domain.entity.Order;
import com.example.demo.domain.entity.OrderDetail;
import com.example.demo.domain.response.orderdetail.ResGetOrderDetailDTO;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.util.SecurityUtil;

@Service
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final ProductService productService;
    private final InventoryRepository inventoryRepository;
    private final CartRepository cartRepository;

    public OrderDetailService(OrderDetailRepository orderDetailRepository, ProductService productService,
            InventoryRepository inventoryRepository, CartRepository cartRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.productService = productService;
        this.inventoryRepository = inventoryRepository;
        this.cartRepository = cartRepository;
    }

    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
        Inventory inventory = this.inventoryRepository.findByProductIdAndSizeId(orderDetail.getProduct().getId(),
                Integer.parseInt(orderDetail.getSize().getName()));
        inventory.setQuantity(inventory.getQuantity() - orderDetail.getQuantity());
        Cart cart = this.cartRepository.findByUserIdAndProductIdAndSizeId(orderDetail.getOrder().getUser().getId(),
                orderDetail.getProduct().getId(), orderDetail.getSize().getId());

        this.inventoryRepository.save(inventory);
        this.cartRepository.delete(cart);
        return this.orderDetailRepository.save(orderDetail);

    }

    public OrderDetail getOrderDetail(long id)
    {
        Optional<OrderDetail> optional = this.orderDetailRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    public List<OrderDetail> getAllOrderDetail(long id) {
        return this.orderDetailRepository.findByOrderId(id);
    }

    public List<ResGetOrderDetailDTO> getAllOrderDetailDTO(long id) {
        List<OrderDetail> orderDetails = this.orderDetailRepository.findByOrderId(id);
        List<ResGetOrderDetailDTO> res = orderDetails.stream()
                .map(o -> new ResGetOrderDetailDTO(
                        id,
                        this.productService.convertToResProductDTO(o.getProduct()),
                        o.getQuantity(),
                        Integer.parseInt(o.getSize().getName())))
                .collect(Collectors.toList());
        return res;
    }

    public void deleteOrderDetail(long id)
    {
        this.orderDetailRepository.deleteById(id);
    }

}
