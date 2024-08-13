package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Order;
import com.example.demo.domain.entity.OrderDetail;
import com.example.demo.domain.response.ResultPaginationDTO;
import com.example.demo.domain.response.order.ResGetOrderDTO;
import com.example.demo.domain.response.orderdetail.ResGetOrderDetailDTO;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailService orderDetailService;
    private final OrderDetailRepository orderDetailRepository;

    public OrderService(OrderRepository orderRepository, OrderDetailService orderDetailService,
            OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailService = orderDetailService;
        this.orderDetailRepository = orderDetailRepository;
    }

    public Order createOrder(Order order) {
        return this.orderRepository.save(order);
    }

    public ResGetOrderDTO getOrder(long id) {
        Order order = this.getOrderById(id);
        ResGetOrderDTO res = new ResGetOrderDTO();
        ResGetOrderDTO.OrderUser user = new ResGetOrderDTO.OrderUser(order.getUser().getId(), order.getUser().getName(),
                order.getAddress(), order.getTelephone());
        List<ResGetOrderDetailDTO> detail = this.orderDetailService.getAllOrderDetailDTO(order.getId());
        res.setId(id);
        res.setStatus(order.getStatus());
        res.setTotal(order.getTotal());
        res.setUser(user);
        res.setOrderDetail(detail);
        return res;
    }

    public ResultPaginationDTO findAllOrderByUserId(long id, Specification<Order> specification, Pageable pageable) {
        ResultPaginationDTO rs = new ResultPaginationDTO();
        Page<Order> pageOrder = this.orderRepository.findByUserId(id, pageable);

        ResultPaginationDTO.Meta mt = new ResultPaginationDTO.Meta();

        mt.setPage(pageable.getPageNumber() + 1);
        mt.setPageSize(pageable.getPageSize());

        mt.setPages(pageOrder.getTotalPages());
        mt.setTotal(pageOrder.getTotalPages());

        rs.setMeta(mt);
        List<Order> orders = pageOrder.getContent();
        List<ResGetOrderDTO> orderDTOs = orders.stream().map(o -> this.getOrder(o.getId()))
                .collect(Collectors.toList());
        rs.setResult(orderDTOs);
        return rs;
    }

    public Order getOrderById(long id) {
        Optional<Order> orderOptional = this.orderRepository.findById(id);
        return orderOptional.isPresent() ? orderOptional.get() : null;
    }

    public void deleteOrder(long id) {
        List<OrderDetail> orderDetails = this.orderDetailService.getAllOrderDetail(id);
        this.orderDetailRepository.deleteAll(orderDetails);
        this.orderRepository.deleteById(id);
    }

}
