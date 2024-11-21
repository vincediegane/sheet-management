package com.sheet.management.sheet.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClasseDTO {
  private Long id;
  private String name;
  private Instant createdDate;
}
