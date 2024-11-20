package com.sheet.management.sheet.controller;

import com.sheet.management.sheet.dto.AuthRequestDTO;
import com.sheet.management.sheet.dto.AuthResponseDTO;
import com.sheet.management.sheet.dto.RegisterRequestDTO;
import com.sheet.management.sheet.dto.UserDTO;
import com.sheet.management.sheet.exception.AppException;
import com.sheet.management.sheet.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
  private static final Logger log = LoggerFactory.getLogger(AuthController.class);
  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @Operation(
      description = "Get endpoint to load user information",
      summary = "This is a summary for getting a user information endpoint",
      responses = {
        @ApiResponse(
          description = "Success",
          responseCode = "200"
        ),
        @ApiResponse(
          description = "Not found",
          responseCode = "404"
        )
      }
  )
  @GetMapping("/me")
  public ResponseEntity<UserDTO> getCurrentUser() throws AppException {
    return ResponseEntity.ok(authService.getCurrentUser());
  }

  @Operation(
      description = "Post endpoint for registering a user",
      summary = "This is a summary for registering a user endpoint",
      responses = {
          @ApiResponse(
              description = "Success",
              responseCode = "201"
          ),
          @ApiResponse(
              description = "Invalid Parameters",
              responseCode = "403"
          )
      }
  )
  @PostMapping("/register")
  public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO request) {
    log.info("request:" , request);
    return ResponseEntity.ok(authService.register(request));
  }

  @Operation(
      description = "Post endpoint for authenticate a user",
      summary = "This is a summary for authenticate a user endpoint",
      responses = {
          @ApiResponse(
              description = "Success",
              responseCode = "200"
          ),
          @ApiResponse(
              description = "Invalid Parameters",
              responseCode = "403"
          )
      }
  )
  @PostMapping("/authenticate")
  public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody AuthRequestDTO request) throws AppException {
    return ResponseEntity.ok(authService.authenticate(request));
  }

//  @GetMapping("/user/profile/{profileId}")
//  public UserDTO getUserByProfile(@PathVariable Long profileId) {
//    return authService.getUserByProfile(profileId);
//  }
}
