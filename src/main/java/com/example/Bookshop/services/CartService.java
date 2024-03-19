package com.example.Bookshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bookshop.entities.Book;
import com.example.Bookshop.entities.Cart;
import com.example.Bookshop.entities.CartItem;

import com.example.Bookshop.repositories.BookRepository;
import com.example.Bookshop.repositories.CartRepository;
import com.example.Bookshop.services.exceptions.CustomEntityNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class CartService {

  @Autowired
  CartRepository cartRepository;
  @Autowired
  CartItemService cartItemService;
  @Autowired
  BookRepository bookRepository;
  @Autowired
  BookService bookService;

  @Transactional
  public Cart getCartById(Long id){
    return cartRepository.findById(id).orElseThrow(() -> new CustomEntityNotFoundException(id));
  }

  @Transactional
  public Cart addItemToCart(Long userId, Long bookId, Integer quantity){
    Cart existingCart = getCartById(userId);
    Book existingBook = bookService.getById(bookId);

    CartItem cartItem = new CartItem();
    cartItem.setQuantity(quantity);

    existingCart.addItem(cartItem);
    existingBook.addItem(cartItem);
    
    return cartRepository.save(existingCart);
  }
 
  @Transactional
  public Cart deleteItemFromCart(Long itemId){

      CartItem existingItem = cartItemService.getById(itemId);

      Cart existingCart = existingItem.getCart();
      Book existingBook = existingItem.getBook();
      
  
      existingCart.removeItem(existingItem);
      existingBook.removeItem(existingItem);
      cartItemService.deleteById(itemId);
      
      return existingCart;
  }
  
  @Transactional
  public Cart clearCart(Long id){
    Cart existingCart = getCartById(id);

      existingCart.getItems().clear();
   
    return cartRepository.save(existingCart);
  }
}
