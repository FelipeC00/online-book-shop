package com.example.Bookshop.web.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class OrderItemResponseDto {
  private Long id;
  private Long orderId;
  private String bookTitle;
  private Integer quantity;
  private BigDecimal price;

  @Override
  public String toString() {
    return "OrderItemDto [id=" + id + ", orderId=" + orderId + ", bookTitle=" + bookTitle + ", quantity=" + quantity
        + ", price=" + price + "]";
  }
}
