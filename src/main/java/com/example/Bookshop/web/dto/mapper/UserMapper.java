package com.example.Bookshop.web.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.example.Bookshop.entities.User;
import com.example.Bookshop.web.dto.UserCreateDto;
import com.example.Bookshop.web.dto.UserResponseDto;

public class UserMapper {

  private static ModelMapper modelMapper = new ModelMapper();

  static {
    modelMapper.typeMap(User.class, UserResponseDto.class)
      .addMappings(mapper ->  mapper.map(src -> {
        if (src.getRole() != null && src.getRole().toString().startsWith("ROLE_")) {
          return src.getRole().toString().substring("ROLE_".length());
        } else {
          return null;
        }
      }, UserResponseDto::setRole));
  }

  public static User toUser(UserCreateDto dto){
    User user = modelMapper.map(dto, User.class);
    return user;
  }

  public static UserResponseDto toDto(User user){
    UserResponseDto dto = modelMapper.map(user, UserResponseDto.class);
    return dto;
  }

  public static List<UserResponseDto> toListDto(List<User> users){
    return users.stream()
    .map(UserMapper::toDto)
    .collect(Collectors.toList());
  }
}
