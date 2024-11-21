package com.sheet.management.sheet.service.impl;

import com.sheet.management.sheet.dto.ClasseDTO;
import com.sheet.management.sheet.dto.FicheDTO;
import com.sheet.management.sheet.dto.ProfileDTO;
import com.sheet.management.sheet.exception.AppException;
import com.sheet.management.sheet.mapper.ProfileMapper;
import com.sheet.management.sheet.mapper.UserMapper;
import com.sheet.management.sheet.model.Profile;
import com.sheet.management.sheet.model.User;
import com.sheet.management.sheet.repository.ProfileRepository;
import com.sheet.management.sheet.service.ProfileService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {
  private final ProfileRepository profileRepository;
  private final ProfileMapper profileMapper;
  private final UserMapper userMapper;

  public ProfileServiceImpl(ProfileRepository profileRepository, ProfileMapper profileMapper, UserMapper userMapper) {
    this.profileRepository = profileRepository;
    this.profileMapper = profileMapper;
    this.userMapper = userMapper;
  }

  @Override
  public List<ProfileDTO> findAll() {
    List<Profile> profiles = profileRepository.findAll();
    return profiles.stream().map(profileMapper::fromProfile).toList();
  }

  @Override
  public ProfileDTO getProfile(Long id) throws AppException {
    Profile profile = profileRepository.findById(id).orElseThrow(() -> new AppException("Profile not found", HttpStatus.NOT_FOUND));
    return profileMapper.fromProfile(profile);
  }

  @Override
  public ProfileDTO getCurrentProfile(Authentication connectedUser) throws AppException {
    User user = (User) connectedUser.getPrincipal();
    Profile profile = profileRepository.findByUserId(user.getId());
    if(profile == null) {
      throw new AppException("Profile not found", HttpStatus.NOT_FOUND);
    }
    return profileMapper.fromProfile(profile);
  }

  @Override
  public ProfileDTO getProfileByUserId(Long userId) throws AppException {
    Profile profile = profileRepository.findByUserId(userId);
    if(profile == null) {
      throw new AppException("Profile not found", HttpStatus.NOT_FOUND);
    }
    return profileMapper.fromProfile(profile);
  }

  @Override
  public ProfileDTO addProfile(ProfileDTO profileDTO, Authentication connectedUser) throws AppException {
    User user = (User) connectedUser.getPrincipal();
    if(user.getProfile() != null) {
      throw new AppException("User already have a profile", HttpStatus.BAD_REQUEST);
    }
    Profile profile = profileMapper.fromProfileDTO(profileDTO);
    profile.setUser(user);
    profile.setCreatedDate(Instant.now());
    Profile savedProfile = profileRepository.save(profile);
    return profileMapper.fromProfile(savedProfile);
  }

  @Override
  public ProfileDTO updateProfile(ProfileDTO profileDTO, Authentication connectedUser) {
    User user = (User) connectedUser.getPrincipal();
    Profile profile = profileMapper.fromProfileDTO(profileDTO, userMapper.fromUser(user));
    Profile savedProfile = profileRepository.save(profile);
    return profileMapper.fromProfile(savedProfile);
  }

  @Override
  public void deleteProfile(Long id) {
    profileRepository.deleteById(id);
  }
}
