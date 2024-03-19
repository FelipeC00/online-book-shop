package com.example.Bookshop.web.dto.mapper;

import org.modelmapper.ModelMapper;

import com.example.Bookshop.entities.Cart;
import com.example.Bookshop.web.dto.CartResponseDto;

public class CartMapper {

  private static ModelMapper modelMapper = new ModelMapper();

  static{
    modelMapper.typeMap(Cart.class, CartResponseDto.class)
    .addMappings(mapper -> mapper.map(src -> src.getTotalPrice(), CartResponseDto::setTotalPrice));
  }

  public static CartResponseDto toDto(Cart cart){
    return modelMapper.map(cart, CartResponseDto.class);
  }

}
