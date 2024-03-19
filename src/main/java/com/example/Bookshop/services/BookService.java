package com.example.Bookshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bookshop.entities.Book;
import com.example.Bookshop.repositories.BookRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class BookService {

  @Autowired
  BookRepository bookRepository;
  
  @Transactional
  public Book save(Book book){
    return bookRepository.save(book);
  }
  
  @Transactional
  public List<Book> getAll(){
    return bookRepository.findAll();
  }

   @Transactional
  public Book getById(Long id){
    return bookRepository.findById(id)
    .orElseThrow(() -> new RuntimeException());
  }
  
  @Transactional
  public List<Book> getByTitle(String title) {
    return bookRepository.findByTitleContainingIgnoreCase(title);
  }
  
  @Transactional
  public Book update(Long id, Book book){
    Book existinBook = bookRepository.findById(id)
    .orElseThrow(() -> new RuntimeException());
    existinBook.setTitle(book.getTitle());
    existinBook.setPublisher(book.getPublisher());
    existinBook.setPrice(book.getPrice());
    existinBook.setStock(book.getStock());
    return bookRepository.save(existinBook);
  }

  public void deleteById(Long id){
    if(bookRepository.existsById(id)){
      bookRepository.deleteById(id);
    }else{
      throw new EntityNotFoundException();
    }
  }
}
