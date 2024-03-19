package com.example.Bookshop.web.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class OrderResponseDto {
  private Long id;
  private Long userId;
  private List<OrderItemResponseDto> orderItems;
  private BigDecimal totalPrice;
  private LocalDateTime orderDate;
  private String status;

  @Override
  public String toString() {
    return "OrderResponseDto [id=" + id + ", userId=" + userId + ", orderItems=" + orderItems + ", totalPrice="
          + totalPrice + ", orderDate=" + orderDate + ", status=" + status + "]";
  }
    
}
