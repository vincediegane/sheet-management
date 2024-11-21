package com.sheet.management.sheet.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileDTO {
  private Long id;
  private String telephone;
  private String address;
  private String specialite;
  private Instant createdDate;
}
