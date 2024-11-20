package com.sheet.management.sheet.dto;

import com.sheet.management.sheet.enumeration.Role;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDTO {
  private Long id;
  private String lastName;
  private String firstName;
  private String email;
  private String username;
  private String password;
  private String address;
  private String speciality;
  private Boolean status;
  private String telephone;
  private Role role;
  private Instant createdDate;
}
