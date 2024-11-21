package com.sheet.management.sheet.mapper;

import com.sheet.management.sheet.dto.ProfileDTO;
import com.sheet.management.sheet.dto.UserDTO;
import com.sheet.management.sheet.model.Profile;
import org.springframework.stereotype.Service;

@Service
public class ProfileMapper {
  private final UserMapper userMapper;

  public ProfileMapper(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  public Profile fromProfileDTO(ProfileDTO profileDTO) {
    return Profile.builder()
        .id(profileDTO.getId())
        .address(profileDTO.getAddress())
        .telephone(profileDTO.getTelephone())
        .specialite(profileDTO.getSpecialite())
        .createdDate(profileDTO.getCreatedDate())
        .build();
  }

  public Profile fromProfileDTO(ProfileDTO profileDTO, UserDTO userDTO) {
    return Profile.builder()
        .id(profileDTO.getId())
        .createdDate(profileDTO.getCreatedDate())
        .specialite(profileDTO.getSpecialite())
        .telephone(profileDTO.getTelephone())
        .address(profileDTO.getAddress())
        .user(userMapper.fromUserDTO(userDTO))
        .build();
  }

  public ProfileDTO fromProfile(Profile profile) {
    return ProfileDTO.builder()
        .id(profile.getId())
        .address(profile.getAddress())
        .telephone(profile.getTelephone())
        .specialite(profile.getSpecialite())
        .createdDate(profile.getCreatedDate())
        .build();
  }
}
