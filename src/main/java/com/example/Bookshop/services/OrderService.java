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

@Service
public class OrderService {

  @Autowired
  OrderRepository orderRepository;
  @Autowired
  CartRepository cartRepository;
  @Autowired
  CartService cartService;

  public Order save(Long cartId){
    Cart existingCart = cartRepository.findById(cartId)
    .orElseThrow(()-> new RuntimeException());

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

  public Order getById(Long id){
    return orderRepository.findById(id)
    .orElseThrow(()-> new RuntimeException());
  }

  public List<Order> getAll(){
    return orderRepository.findAll();
  }


}
