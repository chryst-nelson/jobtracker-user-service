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

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthCtrl {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDto>> register(
            @RequestBody @Valid RegisterDto request) {

        return ResponseEntity.status(201).body(
                ApiResponse.<UserResponseDto>builder()
                        .success(true)
                        .data(authService.register(request))
                        .build());

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponseDto>> login(
            @RequestBody @Valid LoginDto request) {
        return ResponseEntity.status(201).body(
                ApiResponse.<AuthResponseDto>builder()
                        .success(true)
                        .data(authService.login(request))
                        .build());
    }

}
