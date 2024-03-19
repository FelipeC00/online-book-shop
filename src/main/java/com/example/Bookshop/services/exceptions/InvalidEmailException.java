package com.example.Bookshop.services.exceptions;

public class InvalidEmailException extends RuntimeException{

  public InvalidEmailException(String message){
    super(message);
  }

}
