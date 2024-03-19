package com.example.Bookshop.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UserResponseDto {
  private Long id;
  private String username;
  private String role;

  @Override
  public String toString() {
    return "UserResponseDto [id=" + id + ", username=" + username + ", role=" + role + "]";
  }
}
