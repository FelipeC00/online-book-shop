package com.example.Bookshop.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bookshop.entities.Cart;
import com.example.Bookshop.services.CartService;
import com.example.Bookshop.web.dto.CartResponseDto;
import com.example.Bookshop.web.dto.mapper.CartMapper;

@RequestMapping("api/carts")
@RestController
public class CartController {

  @Autowired
  CartService cartService;
  
  @GetMapping("/{id}")
  public ResponseEntity<CartResponseDto> getMyCart(@PathVariable Long id){
    Cart cart = cartService.getCartById(id);
    return ResponseEntity.ok().body(CartMapper.toDto(cart));
  }
  
  @PostMapping("/{userId}/{bookId}/{quantity}")
  public ResponseEntity<CartResponseDto> addItem(@PathVariable Long userId, @PathVariable Long bookId, @PathVariable Integer quantity){
     Cart cart = cartService.addItemToCart(userId, bookId, quantity);
     return ResponseEntity.ok().body(CartMapper.toDto(cart));
  }

  @PatchMapping("/{itemId}")
  public ResponseEntity<CartResponseDto> removeItem(@PathVariable Long itemId){
    Cart cart = cartService.deleteItemFromCart(itemId);
    return ResponseEntity.ok().body(CartMapper.toDto(cart));
  }

  @DeleteMapping("/{cartId}")
  public ResponseEntity<CartResponseDto> emptCart(@PathVariable Long cartId){
    Cart cart = cartService.clearCart(cartId);
    return ResponseEntity.ok().body(CartMapper.toDto(cart));
  }
}
