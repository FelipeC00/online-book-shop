package com.example.Bookshop.web.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;

@Getter
public class ErrorMessage {
   private String path;
   private String method;
   private int status;
   private String statusText;
   private String message;
   @JsonInclude(JsonInclude.Include.NON_NULL)//basically checks if it's null, in which case "errors" isn't sent in the response
   private Map<String, String> errors;

   public ErrorMessage(){
   }

   public ErrorMessage(HttpServletRequest request, HttpStatus status, String message){
    this.path = request.getRequestURI();
    this.method = request.getMethod();
    this.status = status.value();
    this.message = message;
   }

   public ErrorMessage(HttpServletRequest request, HttpStatus status, String message, BindingResult result){
    this(request, status, message);
    addErrors(result);
   }

  private void addErrors(BindingResult result) {
    this.errors = new HashMap<>();
    for(FieldError f: result.getFieldErrors()){
      errors.put(f.getField(), f.getDefaultMessage());
    }

  }

  @Override
  public String toString() {
    return "ErrorMessage [path=" + path + ", method=" + method + ", status=" + status + ", statusText=" + statusText
        + ", message=" + message + ", errors=" + errors + "]";
  }
}

  

