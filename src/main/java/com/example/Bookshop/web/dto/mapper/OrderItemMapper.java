package com.example.Bookshop.web.dto.mapper;

import org.modelmapper.ModelMapper;

import com.example.Bookshop.entities.OrderItem;
import com.example.Bookshop.web.dto.OrderItemResponseDto;

public class OrderItemMapper {
  private static ModelMapper modelMapper = new ModelMapper();


  public static OrderItemResponseDto toDto(OrderItem item){
    return modelMapper.map(item, OrderItemResponseDto.class);
  }
}
