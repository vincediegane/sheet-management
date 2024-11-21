package com.sheet.management.sheet.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Profile {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "profile_id")
  private Long id;
  private String telephone;
  private String address;
  private String specialite;
  @OneToMany(cascade = CascadeType.REMOVE)
  private List<Classe> classes;
  @OneToMany(cascade = CascadeType.REMOVE)
  private List<Fiche> fiches;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", referencedColumnName = "user_id")
  private User user;
  private Instant createdDate;
}
