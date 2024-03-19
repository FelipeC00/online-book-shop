package com.example.Bookshop.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UserCreateDto {

  @NotBlank(message = "Username is mandatory")
  private String username;
  @NotBlank(message = "password is mandatory")
  @Size(min = 6, max = 6)
  private String password;
  @NotBlank(message = "Email is mandatory")
  @Email(message = "Invalid email format")
  private String email;
  
  @Override
  public String toString() {
    return "UserCreateDto [username=" + username + ", password=" + password + ", email=" + email + "]";
  }
}
