package com.example.Bookshop.web.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CartResponseDto {
  private Long id;
  private List<CartItemResponseDto> items = new ArrayList<>();
  private BigDecimal totalPrice;

  @Override
  public String toString() {
    return "CartReponseDto [id=" + id + ", items=" + items + ", totalPrice=" + totalPrice + "]";
  }
}
