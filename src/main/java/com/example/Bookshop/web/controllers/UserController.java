package com.example.Bookshop.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bookshop.entities.User;
import com.example.Bookshop.services.UserService;
import com.example.Bookshop.web.dto.EmailUpdateRequestDto;
import com.example.Bookshop.web.dto.PasswordUpdateRequestDto;
import com.example.Bookshop.web.dto.UserCreateDto;
import com.example.Bookshop.web.dto.UserResponseDto;
import com.example.Bookshop.web.dto.mapper.UserMapper;

import jakarta.validation.Valid;

@RequestMapping("api/users")
@RestController
public class UserController {

  @Autowired
  UserService userService;
  
  @PostMapping
  public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserCreateDto dto){
    User user = userService.save(UserMapper.toUser(dto));
    return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(user));
  }

  @GetMapping
  public ResponseEntity<List<UserResponseDto>> getAll(){
    List<User> users = userService.getAll();
    return ResponseEntity.ok().body(UserMapper.toListDto(users));
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDto> getById(@PathVariable Long id){
    User user = userService.getById(id);
    return ResponseEntity.ok().body(UserMapper.toDto(user));
  }

  @PatchMapping("/{id}/password")
  public ResponseEntity<?> updatePassword(@PathVariable Long id, @Valid @RequestBody PasswordUpdateRequestDto dto){
    userService.updatePassword(id, dto);
    return ResponseEntity.ok("Password updated successfully");
  }
  
  @PatchMapping("/{id}/email")
  public ResponseEntity<?> updateEmail(@PathVariable Long id, @Valid @RequestBody EmailUpdateRequestDto dto){
    userService.updateEmail(id, dto);
    return ResponseEntity.ok("Email updated successfully");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id){
    userService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
