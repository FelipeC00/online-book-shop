package com.example.Bookshop.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class PasswordUpdateRequestDto {
  
  @NotBlank(message = "password is mandatory")
  @Size(min = 6, max = 6)
  private String currentPassword;
  @NotBlank(message = "password is mandatory")
  @Size(min = 6, max = 6)
  private String newPassword;
  @NotBlank(message = "password is mandatory")
  @Size(min = 6, max = 6)
  private String confirmPassword;
  
  @Override
  public String toString() {
    return "PasswordUpdateRequestDto [currentPassword=" + currentPassword + ", newPassword=" + newPassword
        + ", confirmPassword=" + confirmPassword + "]";
  }

  
}
