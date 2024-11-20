package com.sheet.management.sheet.model;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Enseignant {
    private Long id;
    private String last_name;
    private String first_name;
    private String email;
    private String password;
    private String speciality;
    private Boolean status;
    private Instant createdDate;
}
