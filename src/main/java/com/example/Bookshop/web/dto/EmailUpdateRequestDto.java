package com.example.Bookshop.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class EmailUpdateRequestDto {
  
  @NotBlank(message = "Email is mandatory")
  @Email(message = "Invalid email format")
  private String currentEmail;
  @NotBlank(message = "Email is mandatory")
  @Email(message = "Invalid email format")
  private String newEmail;
  @NotBlank(message = "Email is mandatory")
  @Email(message = "Invalid email format")
  private String confirmEmail;

  @Override
  public String toString() {
    return "EmailUpdateRequestDto [currentEmail=" + currentEmail + ", newEmail=" + newEmail + ", confirmEmail="
        + confirmEmail + "]";
  }
}
