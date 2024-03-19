package com.example.Bookshop.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bookshop.entities.Book;
import com.example.Bookshop.services.BookService;
import com.example.Bookshop.web.dto.BookResponseDto;
import com.example.Bookshop.web.dto.mapper.BookMapper;


@RequestMapping("api/books")
@RestController
public class BookController {

  @Autowired
  BookService bookService;
  
  @PostMapping
  public ResponseEntity<Book> create(@RequestBody Book book){
    Book b = bookService.save(book);
    return ResponseEntity.status(HttpStatus.CREATED).body(b);
  }

  @GetMapping
  public ResponseEntity<List<BookResponseDto>> getAll(){
    List<Book> books = bookService.getAll();
    return ResponseEntity.ok().body(BookMapper.toListDto(books));
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<BookResponseDto> getById(@PathVariable Long id){
    Book book = bookService.getById(id);
    return ResponseEntity.ok().body(BookMapper.toDto(book));
  }
  
  @GetMapping("/search")
  public ResponseEntity<List<BookResponseDto>> getByTitle(@RequestParam String title){
    List<Book> books = bookService.getByTitle(title);
    return ResponseEntity.ok().body(BookMapper.toListDto(books));
  }

  @PutMapping("/{id}")
  public ResponseEntity<BookResponseDto> update(@PathVariable Long id, @RequestBody Book book){
    Book b = bookService.update(id, book);
    return ResponseEntity.ok().body(BookMapper.toDto(b));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id){
    bookService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
