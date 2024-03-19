package com.example.Bookshop.web.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.example.Bookshop.entities.Order;
import com.example.Bookshop.web.dto.OrderResponseDto;

public class OrderMapper {

   private static ModelMapper modelMapper = new ModelMapper();

   static {
    modelMapper.typeMap(Order.class, OrderResponseDto.class)
      .addMappings(mapper ->  mapper.map(src -> {
        if (src.getStatus() != null) {
          return src.getStatus();
        } else {
          return null;
        }
      }, OrderResponseDto::setStatus));
  }


   public static OrderResponseDto toDto(Order order){
    return modelMapper.map(order, OrderResponseDto.class);
   }

    public static List<OrderResponseDto> toListDto(List<Order> users){
    return users.stream()
    .map(OrderMapper::toDto)
    .collect(Collectors.toList());
  }
}
