package com.chigolite.jobtracker_user_service.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chigolite.jobtracker_user_service.auth.dtos.AuthResponseDto;
import com.chigolite.jobtracker_user_service.auth.dtos.LoginDto;
import com.chigolite.jobtracker_user_service.auth.dtos.RegisterDto;
import com.chigolite.jobtracker_user_service.auth.service.AuthService;
import com.chigolite.jobtracker_user_service.common.util.ApiResponse;
import com.chigolite.jobtracker_user_service.user.dto.UserResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Register and login endpoints")

public class AuthCtrl {

        private final AuthService authService;

        @Operation(summary = "Register a new user")
        @ApiResponses({
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "User created successfully"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Email already exists")
        })
        @PostMapping("/register")
        public ResponseEntity<ApiResponse<UserResponseDto>> register(
                        @RequestBody @Valid RegisterDto request) {

                return ResponseEntity.status(201).body(
                                ApiResponse.<UserResponseDto>builder()
                                                .success(true)
                                                .data(authService.register(request))
                                                .build());

        }

        @Operation(summary = "Login an existing user")
        @ApiResponses({
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User logged in successfully"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Invalid credentials")
        })

        @PostMapping("/login")
        public ResponseEntity<ApiResponse<AuthResponseDto>> login(
                        @RequestBody @Valid LoginDto request) {
                return ResponseEntity.status(200).body(
                                ApiResponse.<AuthResponseDto>builder()
                                                .success(true)
                                                .data(authService.login(request))
                                                .build());
        }

}
