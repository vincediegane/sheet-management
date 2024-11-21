package com.sheet.management.sheet.service.impl;

import com.sheet.management.sheet.dto.FicheDTO;
import com.sheet.management.sheet.dto.ProfileDTO;
import com.sheet.management.sheet.exception.AppException;
import com.sheet.management.sheet.mapper.FicheMapper;
import com.sheet.management.sheet.model.Fiche;
import com.sheet.management.sheet.repository.FicheRepository;
import com.sheet.management.sheet.service.FicheService;
import com.sheet.management.sheet.service.ProfileService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class FicheServiceImpl implements FicheService {
  private final FicheRepository ficheRepository;
  private final FicheMapper ficheMapper;
  private final ProfileService profileService;

  public FicheServiceImpl(FicheRepository ficheRepository, FicheMapper ficheMapper, ProfileService profileService) {
    this.ficheRepository = ficheRepository;
    this.ficheMapper = ficheMapper;
    this.profileService = profileService;
  }

  @Override
  public List<FicheDTO> findAll() {
    List<Fiche> fiches = ficheRepository.findAll();
    return fiches.stream().map(ficheMapper::fromFiche).toList();
  }

  @Override
  public List<FicheDTO> getProfileFiches(Long profileId) {
    List<Fiche> fiches = ficheRepository.findProfileFiches(profileId);
    return fiches.stream().map(ficheMapper::fromFiche).toList();
  }

  @Override
  public FicheDTO findById(Long id) throws AppException {
    Fiche fiche = ficheRepository.findById(id).orElseThrow(() -> new AppException("Education not found", HttpStatus.NOT_FOUND));
    return ficheMapper.fromFiche(fiche);
  }

  @Override
  public FicheDTO addFiche(FicheDTO ficheDTO, Authentication connectedUser) throws AppException {
    ProfileDTO profileDTO = profileService.getCurrentProfile(connectedUser);
    ficheDTO.setCreatedDate(Instant.now());
    Fiche fiche = ficheRepository.save(ficheMapper.fromFicheDTO(ficheDTO, profileDTO));
    return ficheMapper.fromFiche(fiche);
  }

  @Override
  public FicheDTO updateFiche(FicheDTO ficheDTO, Authentication connectedUser) throws AppException {
    ProfileDTO profileDTO = profileService.getCurrentProfile(connectedUser);
    Fiche fiche = ficheRepository.save(ficheMapper.fromFicheDTO(ficheDTO, profileDTO));
    return ficheMapper.fromFiche(fiche);
  }

  @Override
  public void deleteFiche(Long id) {
    ficheRepository.deleteById(id);
  }
}
