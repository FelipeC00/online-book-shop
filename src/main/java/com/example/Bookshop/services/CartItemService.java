package com.example.Bookshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bookshop.entities.CartItem;
import com.example.Bookshop.repositories.CartItemRepository;

@Service
public class CartItemService {

  @Autowired
  CartItemRepository cartItemRepository;

  public CartItem save(CartItem cartItem){
    return cartItemRepository.save(cartItem);
  }

  

  
}
