package com.example.Bookshop.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bookshop.entities.Order;
import com.example.Bookshop.services.OrderService;
import com.example.Bookshop.web.dto.OrderResponseDto;
import com.example.Bookshop.web.dto.mapper.OrderMapper;

@RequestMapping("api/orders")
@RestController
public class OrderController {

  @Autowired
  OrderService orderService;

  @PostMapping("/{cartId}")
  public ResponseEntity<OrderResponseDto> createOrder(@PathVariable Long cartId){
    Order order = orderService.save(cartId);
    return ResponseEntity.ok().body(OrderMapper.toDto(order));
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id){
    Order order = orderService.getById(id);
    return ResponseEntity.ok().body(OrderMapper.toDto(order));
  }
  
  @GetMapping
  public ResponseEntity<List<OrderResponseDto>> getAllOrders(){
    List<Order> orders = orderService.getAll();
    return ResponseEntity.ok().body(OrderMapper.toListDto(orders));
  }


}
