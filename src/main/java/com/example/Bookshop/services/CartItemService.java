package com.example.Bookshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bookshop.entities.CartItem;
import com.example.Bookshop.repositories.CartItemRepository;
import com.example.Bookshop.services.exceptions.CustomEntityNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class CartItemService {

  @Autowired
  CartItemRepository cartItemRepository;
  
  @Transactional
  public CartItem save(CartItem cartItem){
    return cartItemRepository.save(cartItem);
  }
  
  @Transactional
  public CartItem getById(Long id){
    return cartItemRepository.findById(id)
    .orElseThrow(() -> new CustomEntityNotFoundException(id));
  }
  
  @Transactional
  public void deleteById(Long id){
    if(cartItemRepository.existsById(id)){
      cartItemRepository.deleteById(id);
    }
    else{
      throw new CustomEntityNotFoundException(id);
    }
  } 
}
