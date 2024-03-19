package com.example.Bookshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bookshop.entities.User;
import com.example.Bookshop.repositories.UserRepository;
import com.example.Bookshop.web.dto.EmailUpdateRequestDto;
import com.example.Bookshop.web.dto.PasswordUpdateRequestDto;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;



@Service
public class UserService {

  @Autowired
  UserRepository userRepository;
 

  @Transactional
  public User save(User user){
    return userRepository.save(user);
  }
  
  @Transactional
  public List<User> getAll(){
    return userRepository.findAll();
  }

  @Transactional
  public User getById(Long id){
    return userRepository.findById(id)
    .orElseThrow(() -> new RuntimeException());
  }
  
  @Transactional
  public void updatePassword(long id, PasswordUpdateRequestDto dto){
    User existingUser = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));

    if (!dto.getCurrentPassword().equals(existingUser.getPassword())) {
        throw new RuntimeException("Current password is incorrect");
    }

    if (!dto.getNewPassword().equals(dto.getConfirmPassword())) {
        throw new RuntimeException("New password and confirm password don't match");
    }

    existingUser.setPassword(dto.getNewPassword());
    userRepository.save(existingUser);
  }
  
  @Transactional
  public void updateEmail(Long id, EmailUpdateRequestDto dto){
    User existingUser = userRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("User not found"));

    if(!dto.getCurrentEmail().equals(existingUser.getEmail())){
      throw new RuntimeException("Current email is incorrect");
    }

    if(!dto.getNewEmail().equals(dto.getConfirmEmail())){
      throw new RuntimeException("New email and confirm email don't match");
    }

    existingUser.setEmail(dto.getNewEmail());
    userRepository.save(existingUser);
  }
  
  @Transactional
  public void deleteById(Long id){
    if(userRepository.existsById(id)){
      userRepository.deleteById(id);
    }else{
      throw new EntityNotFoundException();
    }
  }
}
