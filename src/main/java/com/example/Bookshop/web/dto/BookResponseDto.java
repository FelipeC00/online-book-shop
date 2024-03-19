package com.example.Bookshop.web.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class BookResponseDto {
  private String title;
  private String publisher;
  private BigDecimal price;

  @Override
  public String toString() {
    return "BookResponseDto [title=" + title + ", publisher=" + publisher + ", price=" + price + "]";
  }
}
