package com.example.Bookshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.Bookshop.entities.User;
import com.example.Bookshop.repositories.UserRepository;
import com.example.Bookshop.services.exceptions.CustomEntityNotFoundException;
import com.example.Bookshop.services.exceptions.InvalidEmailException;
import com.example.Bookshop.services.exceptions.InvalidPasswordException;
import com.example.Bookshop.services.exceptions.UniqueUserViolationException;
import com.example.Bookshop.web.dto.EmailUpdateRequestDto;
import com.example.Bookshop.web.dto.PasswordUpdateRequestDto;

import jakarta.transaction.Transactional;



@Service
public class UserService {

  @Autowired
  UserRepository userRepository;
 

  @Transactional
  public User save(User user){
    try {
      return userRepository.save(user);
    } 
    catch (DataIntegrityViolationException e) {
      throw new UniqueUserViolationException(e.getMessage());
    }
    
  }
  
  @Transactional
  public List<User> getAll(){
    return userRepository.findAll();
  }

  @Transactional
  public User getById(Long id){
    return userRepository.findById(id)
    .orElseThrow(() -> new CustomEntityNotFoundException(id));
  }
  
  @Transactional
  public void updatePassword(long id, PasswordUpdateRequestDto dto){
    User existingUser = getById(id);
  
    if (!dto.getCurrentPassword().equals(existingUser.getPassword())) {
        throw new InvalidPasswordException("Current password is incorrect");
    }

    if (!dto.getNewPassword().equals(dto.getConfirmPassword())) {
        throw new InvalidPasswordException("New and confirm password don't match");
    }

    existingUser.setPassword(dto.getNewPassword());
    userRepository.save(existingUser);
  }
  
  @Transactional
  public void updateEmail(Long id, EmailUpdateRequestDto dto){
    User existingUser = getById(id);

    if(!dto.getCurrentEmail().equals(existingUser.getEmail())){
      throw new InvalidEmailException("Current e-mail is incorrect");
    }

    if(!dto.getNewEmail().equals(dto.getConfirmEmail())){
      throw new InvalidEmailException("New and confirm e-mail don't match");
    }

    existingUser.setEmail(dto.getNewEmail());
    userRepository.save(existingUser);
  }
  
  @Transactional
  public void deleteById(Long id){
    if(userRepository.existsById(id)){
      userRepository.deleteById(id);
    }else{
      throw new CustomEntityNotFoundException(id);
    }
  }
}
