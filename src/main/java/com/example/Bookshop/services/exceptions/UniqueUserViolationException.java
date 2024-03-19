package com.example.Bookshop.services.exceptions;

public class UniqueUserViolationException extends RuntimeException{

  public UniqueUserViolationException(String message){
    super(message);
  }

}
