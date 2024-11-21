package com.sheet.management.sheet.service;

import com.sheet.management.sheet.dto.FicheDTO;
import com.sheet.management.sheet.exception.AppException;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface FicheService {
  List<FicheDTO> findAll();
  List<FicheDTO> getProfileFiches(Long profileId);
  FicheDTO findById(Long id) throws AppException;
  FicheDTO addFiche(FicheDTO ficheDTO, Authentication connectedUser) throws AppException;
  FicheDTO updateFiche(FicheDTO ficheDTO, Authentication connectedUser) throws AppException;
  void deleteFiche(Long id);
}
