package com.example.Bookshop.web.dto.mapper;

import org.modelmapper.ModelMapper;
import com.example.Bookshop.entities.CartItem;
import com.example.Bookshop.web.dto.CartItemResponseDto;

public class CartItemMapper {

  private static ModelMapper modelMapper = new ModelMapper();

    static {
      modelMapper.typeMap(CartItem.class, CartItemResponseDto.class)
          .addMappings(mapper -> {
            mapper.map(src -> src.getTotalPrice(),CartItemResponseDto::setPrice);
            mapper.map(src -> src.getBook().getTitle(), CartItemResponseDto::setBookTitle);
          });
    }

    public static CartItemResponseDto toDto(CartItem cartItem) {
        return modelMapper.map(cartItem, CartItemResponseDto.class);
    }
}
