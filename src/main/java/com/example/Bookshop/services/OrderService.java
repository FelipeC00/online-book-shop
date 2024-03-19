package com.example.Bookshop.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bookshop.entities.Cart;
import com.example.Bookshop.entities.CartItem;
import com.example.Bookshop.entities.Order;
import com.example.Bookshop.entities.OrderItem;
import com.example.Bookshop.entities.enums.OrderStatus;
import com.example.Bookshop.repositories.CartRepository;
import com.example.Bookshop.repositories.OrderRepository;
import com.example.Bookshop.services.exceptions.CustomEntityNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

  @Autowired
  OrderRepository orderRepository;
  @Autowired
  CartRepository cartRepository;
  @Autowired
  CartService cartService;
  
  @Transactional
  public Order save(Long cartId){
    Cart existingCart = cartService.getCartById(cartId);

    if(existingCart.getItems().isEmpty()){
      throw new IllegalArgumentException("Cart is empty");
    }

    Order order = new Order();
    order.setUser(existingCart.getUser());
    order.setOrderDate(LocalDateTime.now());
    order.setStatus(OrderStatus.PENDING);

    for(CartItem item: existingCart.getItems()){

      OrderItem orderItem = new OrderItem();
      orderItem.setBook(item.getBook());
      orderItem.setQuantity(item.getQuantity());
      orderItem.setPrice(item.getTotalPrice());
      orderItem.setOrder(order);
      order.getOrderItems().add(orderItem);
    }

    order.setTotalPrice(existingCart.getTotalPrice());
    
    cartService.clearCart(cartId);

    return orderRepository.save(order);
  }
  
  @Transactional
  public Order getById(Long id){
    return orderRepository.findById(id)
    .orElseThrow(()-> new CustomEntityNotFoundException(id));
  }
  
  @Transactional
  public List<Order> getAll(){
    return orderRepository.findAll();
  }


}
