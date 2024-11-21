package com.sheet.management.sheet.service;

import com.sheet.management.sheet.dto.AuthRequestDTO;
import com.sheet.management.sheet.dto.AuthResponseDTO;
import com.sheet.management.sheet.dto.RegisterRequestDTO;
import com.sheet.management.sheet.dto.UserDTO;
import com.sheet.management.sheet.exception.AppException;

public interface AuthService {
  AuthResponseDTO register(RegisterRequestDTO request);
  AuthResponseDTO authenticate(AuthRequestDTO request) throws AppException;
  UserDTO getCurrentUser() throws AppException;
  UserDTO getUserByProfile(Long profileId);
}
