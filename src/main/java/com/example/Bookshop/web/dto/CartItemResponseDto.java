package com.example.Bookshop.web.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CartItemResponseDto {
  private Long id;
  private Integer quantity;
  private String bookTitle;
  private BigDecimal price;
  @Override
  public String toString() {
    return "CartItemResponseDto [id=" + id + ", quantity=" + quantity + ", bookTitle=" + bookTitle + ", price=" + price
        + "]";
  } 

  
}
