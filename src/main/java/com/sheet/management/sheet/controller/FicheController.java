package com.sheet.management.sheet.controller;

import com.sheet.management.sheet.dto.FicheDTO;
import com.sheet.management.sheet.exception.AppException;
import com.sheet.management.sheet.service.FicheService;
import com.sheet.management.sheet.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fiches")
public class FicheController {
  private final FicheService ficheService;

  public FicheController(FicheService ficheService) {
    this.ficheService = ficheService;
  }

  @GetMapping("/")
  public List<FicheDTO> getFiches() {
    return ficheService.findAll();
  }

  @GetMapping("/fiche/{profileId}")
  public List<FicheDTO> getProfileFiches(@PathVariable Long profileId) {
    return ficheService.getProfileFiches(profileId);
  }

  @GetMapping("/{id}")
  public FicheDTO getFiche(@PathVariable Long id) throws AppException {
    return ficheService.findById(id);
  }

  @PostMapping("/")
  public FicheDTO addFiche(@RequestBody FicheDTO ficheDTO, Authentication connectedUser) throws AppException {
    return ficheService.addFiche(ficheDTO, connectedUser);
  }

  @PutMapping("/{id}")
  public FicheDTO updateEducation(@PathVariable Long id, @RequestBody FicheDTO ficheDTO, Authentication connectedUser) throws AppException {
    ficheDTO.setId(id);
    return ficheService.updateFiche(ficheDTO, connectedUser);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteFiche(@PathVariable Long id) {
    ficheService.deleteFiche(id);
    return ResponseEntity.ok("deleted");
  }
}
