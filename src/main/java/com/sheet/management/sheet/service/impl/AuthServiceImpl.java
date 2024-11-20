package com.sheet.management.sheet.service.impl;

import com.sheet.management.sheet.config.JwtService;
import com.sheet.management.sheet.dto.AuthRequestDTO;
import com.sheet.management.sheet.dto.AuthResponseDTO;
import com.sheet.management.sheet.dto.RegisterRequestDTO;
import com.sheet.management.sheet.dto.UserDTO;
import com.sheet.management.sheet.exception.AppException;
import com.sheet.management.sheet.mapper.UserMapper;
import com.sheet.management.sheet.model.User;
import com.sheet.management.sheet.repository.UserRepository;
import com.sheet.management.sheet.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.nio.CharBuffer;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final UserMapper userMapper;

  public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
    this.userMapper = userMapper;
  }

  @Override
  public AuthResponseDTO register(RegisterRequestDTO request) {
    var user = User.builder()
      .firstName(request.getFirstName())
      .lastName(request.getLastName())
      .address(request.getLastName())
      .email(request.getEmail())
      .username(request.getEmail())
      .password(passwordEncoder.encode(request.getPassword()))
      .role(request.getRole())
      .status(true)
      .createdDate(Instant.now())
    .build();

    userRepository.save(user);

    Map<String, Object> claims = new HashMap<>();
    claims.put("firstName", user.getFirstName());
    claims.put("lastName", user.getLastName());
    claims.put("role", user.getRole());

    var jwtToken = jwtService.generateToken(claims, user);
    AuthResponseDTO response = new AuthResponseDTO();
    response.setToken(jwtToken);
    return response;
  }

  @Override
  public AuthResponseDTO authenticate(AuthRequestDTO request) throws AppException {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        request.getEmail(),
        request.getPassword()
      )
    );
    var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
    Map<String, Object> claims = new HashMap<>();
    claims.put("firstName", user.getFirstName());
    claims.put("lastName", user.getLastName());
    claims.put("role", user.getRole());

    AuthResponseDTO response = new AuthResponseDTO();
    if(passwordEncoder.matches(CharBuffer.wrap(request.getPassword()), user.getPassword())) {
      var jwtToken = jwtService.generateToken(claims, user);
      response.setToken(jwtToken);
      return response;
    }

    throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
  }

  @Override
  public UserDTO getCurrentUser() throws AppException {
    log.info("auth user");
    // Recuperer l'objet Authentication a partir du contexte de securite:
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    // Verifier si l'utilisateur est authentifie:
    if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
      User currentUser = (User) authentication.getPrincipal();
      return userMapper.fromUser(currentUser);
    }
    throw new AppException("No auth user found", HttpStatus.FORBIDDEN);
  }

//  @Override
//  public UserDTO getUserByProfile(Long profileId) {
//    User user = userRepository.findByProfileId(profileId);
//    return userMapper.fromUser(user);
//  }
}
