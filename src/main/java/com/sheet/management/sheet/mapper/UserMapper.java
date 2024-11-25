package com.sheet.management.sheet.mapper;

import com.sheet.management.sheet.dto.UserDTO;
import com.sheet.management.sheet.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
  public User fromUserDTO(UserDTO userDTO) {
    return User.builder()
        .id(userDTO.getId())
        .username(userDTO.getUsername())
        .firstName(userDTO.getFirstName())
        .lastName(userDTO.getLastName())
        .email(userDTO.getEmail())
        .createdDate(userDTO.getCreatedDate())
        .role(userDTO.getRole())
        .password(userDTO.getPassword())
        .status(userDTO.getStatus())
        .build();
  }

  public UserDTO fromUser(User user) {
    return UserDTO.builder()
        .id(user.getId())
        .username(user.getUsername())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .password(user.getPassword())
        .email(user.getEmail())
        .role(user.getRole())
        .createdDate(user.getCreatedDate())
        .status(user.getStatus())
        .build();
  }
}
