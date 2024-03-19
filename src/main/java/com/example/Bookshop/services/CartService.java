package com.example.Bookshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bookshop.entities.Book;
import com.example.Bookshop.entities.Cart;
import com.example.Bookshop.entities.CartItem;

import com.example.Bookshop.repositories.BookRepository;
import com.example.Bookshop.repositories.CartItemRepository;
import com.example.Bookshop.repositories.CartRepository;

import jakarta.transaction.Transactional;

@Service
public class CartService {

  @Autowired
  CartRepository cartRepository;
  @Autowired
  CartItemRepository cartItemRepository;
  @Autowired
  BookRepository bookRepository;

  @Transactional
  public Cart getCartById(Long id){
    return cartRepository.findById(id).orElseThrow(() -> new RuntimeException());
  }

  @Transactional
  public Cart addItemToCart(Long userId, Long bookId, Integer quantity){
    Cart existingCart = cartRepository.findById(userId)
    .orElseThrow(()-> new RuntimeException());
    Book existingBook = bookRepository.findById(bookId)
    .orElseThrow(() -> new RuntimeException());

    CartItem cartItem = new CartItem();
    cartItem.setQuantity(quantity);

    existingCart.addItem(cartItem);
    existingBook.addItem(cartItem);
    
    return cartRepository.save(existingCart);
  }
 
  @Transactional
  public Cart deleteItemFromCart(Long itemId){

      CartItem existingItem = cartItemRepository.findById(itemId)
          .orElseThrow(() -> new RuntimeException("Item with id " + itemId + " not found"));

      Cart existingCart = existingItem.getCart();
      Book existingBook = existingItem.getBook();
      
  
      existingCart.removeItem(existingItem);
      existingBook.removeItem(existingItem);
      cartItemRepository.delete(existingItem);
      
      return existingCart;
  }

  public Cart clearCart(Long id){
    Cart existingCart = cartRepository.findById(id)
    .orElseThrow(()-> new RuntimeException());

      existingCart.getItems().clear();
   
    return cartRepository.save(existingCart);
  }
}
