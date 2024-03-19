package com.example.Bookshop.services.exceptions;

public class InvalidPasswordException extends RuntimeException{

  public InvalidPasswordException(String message){
    super(message);
  }

}
