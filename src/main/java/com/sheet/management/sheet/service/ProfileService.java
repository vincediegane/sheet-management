package com.sheet.management.sheet.service;

import com.sheet.management.sheet.exception.AppException;
import org.springframework.security.core.Authentication;
import com.sheet.management.sheet.dto.ProfileDTO;
import java.util.List;

public interface ProfileService {
  List<ProfileDTO> findAll();
  ProfileDTO getProfile(Long id) throws AppException;
  ProfileDTO getCurrentProfile(Authentication connectedUser) throws AppException;
  ProfileDTO getProfileByUserId(Long userId) throws AppException;
  ProfileDTO addProfile(ProfileDTO profileDTO, Authentication connectedUser) throws AppException;
  ProfileDTO updateProfile(ProfileDTO profileDTO, Authentication connectedUser);
  void deleteProfile(Long id);
}
