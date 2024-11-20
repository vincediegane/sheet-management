package com.sheet.management.sheet.dto;

import com.sheet.management.sheet.enumeration.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDTO {
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private Role role;
}