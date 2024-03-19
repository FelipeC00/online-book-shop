package com.example.Bookshop.web.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.example.Bookshop.entities.Book;
import com.example.Bookshop.web.dto.BookResponseDto;

public class BookMapper {
  private static ModelMapper modelMapper = new ModelMapper();

  public static BookResponseDto toDto(Book book){
    return modelMapper.map(book, BookResponseDto.class);
  }

  public static List<BookResponseDto> toListDto(List<Book> books){
    return books.stream()
    .map(BookMapper::toDto)
    .collect(Collectors.toList());
  }

}
