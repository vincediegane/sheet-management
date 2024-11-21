package com.sheet.management.sheet.mapper;

import com.sheet.management.sheet.dto.FicheDTO;
import com.sheet.management.sheet.dto.ProfileDTO;
import com.sheet.management.sheet.model.Fiche;
import org.springframework.stereotype.Service;

@Service
public class FicheMapper {
  private final ProfileMapper profileMapper;

  public FicheMapper(ProfileMapper profileMapper) {
    this.profileMapper = profileMapper;
  }

  public FicheDTO fromFiche(Fiche fiche) {
    return FicheDTO.builder()
      .id(fiche.getId())
      .title(fiche.getTitle())
      .goal(fiche.getGoal())
      .content(fiche.getContent())
      .createdDate(fiche.getCreatedDate())
      .build();
  }

  public Fiche fromFicheDTO(FicheDTO ficheDTO) {
    return Fiche.builder()
      .id(ficheDTO.getId())
      .title(ficheDTO.getTitle())
      .content(ficheDTO.getContent())
      .goal(ficheDTO.getGoal())
      .createdDate(ficheDTO.getCreatedDate())
      .build();
  }

  public Fiche fromFicheDTO(FicheDTO ficheDTO, ProfileDTO profileDTO) {
    return Fiche.builder()
        .id(ficheDTO.getId())
        .title(ficheDTO.getTitle())
        .content(ficheDTO.getContent())
        .goal(ficheDTO.getGoal())
        .createdDate(ficheDTO.getCreatedDate())
        .profile(profileMapper.fromProfileDTO(profileDTO))
        .build();
  }
}
