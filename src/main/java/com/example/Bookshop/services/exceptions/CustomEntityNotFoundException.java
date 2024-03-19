package com.example.Bookshop.services.exceptions;

public class CustomEntityNotFoundException extends RuntimeException{

  public CustomEntityNotFoundException(Object id){
    super("Entity not found. Id = "+id);
  }

}
