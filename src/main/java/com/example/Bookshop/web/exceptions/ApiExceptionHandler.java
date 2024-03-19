package com.example.Bookshop.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.Bookshop.services.exceptions.CustomEntityNotFoundException;
import com.example.Bookshop.services.exceptions.InvalidEmailException;
import com.example.Bookshop.services.exceptions.InvalidPasswordException;
import com.example.Bookshop.services.exceptions.UniqueUserViolationException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)//when the user provides info in an invalid format
  public ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException e,
   HttpServletRequest request, BindingResult result){
    
    log.error("API error", e);
    return ResponseEntity
    .status(HttpStatus.UNPROCESSABLE_ENTITY)
    .contentType(MediaType.APPLICATION_JSON)
    .body(new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), result));
  }
  
  @ExceptionHandler(UniqueUserViolationException.class)
  public ResponseEntity<ErrorMessage> uniqueViolationException(RuntimeException e, HttpServletRequest request){

    log.error("API error", e);
    return ResponseEntity
    .status(HttpStatus.CONFLICT)
    .contentType(MediaType.APPLICATION_JSON)
    .body(new ErrorMessage(request, HttpStatus.CONFLICT, e.getMessage()));
  }

  @ExceptionHandler(CustomEntityNotFoundException.class)
  public ResponseEntity<ErrorMessage> entityNotFoundException(CustomEntityNotFoundException e, HttpServletRequest request){
    
    log.error("API error", e);
    return ResponseEntity
    .status(HttpStatus.NOT_FOUND)
    .contentType(MediaType.APPLICATION_JSON)
    .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, e.getMessage()));
  }

  @ExceptionHandler(InvalidPasswordException.class)
  public ResponseEntity<ErrorMessage> invalidPasswordException(InvalidPasswordException e, HttpServletRequest request){
    
    log.error("API error", e);
    return ResponseEntity
    .status(HttpStatus.BAD_REQUEST)
    .contentType(MediaType.APPLICATION_JSON)
    .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, e.getMessage()));
  }

  @ExceptionHandler(InvalidEmailException.class)
  public ResponseEntity<ErrorMessage> invalidEmailException(InvalidEmailException e, HttpServletRequest request){
    
    log.error("API error", e);
    return ResponseEntity
    .status(HttpStatus.BAD_REQUEST)
    .contentType(MediaType.APPLICATION_JSON)
    .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, e.getMessage()));
  }

}