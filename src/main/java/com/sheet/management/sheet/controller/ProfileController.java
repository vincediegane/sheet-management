package com.sheet.management.sheet.controller;

import com.sheet.management.sheet.dto.ProfileDTO;
import com.sheet.management.sheet.exception.AppException;
import com.sheet.management.sheet.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {
  private final ProfileService profileService;

  public ProfileController(ProfileService profileService) {
    this.profileService = profileService;
  }

  @GetMapping("/")
  public List<ProfileDTO> getProfiles() {
    return profileService.findAll();
  }

  @GetMapping("/byId/{id}")
  public ProfileDTO getProfile(@PathVariable Long id) throws AppException {
    return profileService.getProfile(id);
  }

  @GetMapping("/users/{userId}")
  public ProfileDTO getUserProfile(@PathVariable Long userId) throws AppException {
    return profileService.getProfileByUserId(userId);
  }

  @GetMapping("/me")
  public ProfileDTO getCurrentProfile(Authentication connectedUser) throws AppException {
    return profileService.getCurrentProfile(connectedUser);
  }

  @PostMapping("/")
  public ProfileDTO addProfile(@RequestBody ProfileDTO profileDTO, Authentication connectedUser) throws AppException {
    return profileService.addProfile(profileDTO, connectedUser);
  }

  @PutMapping("/{id}")
  public ProfileDTO updateProfile(@PathVariable Long id, @RequestBody ProfileDTO profileDTO, Authentication connectedUser) {
    profileDTO.setId(id);
    return profileService.updateProfile(profileDTO, connectedUser);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteProfile(@PathVariable Long id) {
    profileService.deleteProfile(id);
    return ResponseEntity.ok("deleted");
  }
}
