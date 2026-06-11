package com.chigolite.jobtracker_user_service.user.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chigolite.jobtracker_user_service.common.util.ApiResponse;
import com.chigolite.jobtracker_user_service.user.dto.UserResponseDto;
import com.chigolite.jobtracker_user_service.user.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserCtrl {

    private final UserServiceImpl userService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.<UserResponseDto>builder()
                .success(true)
                .data(userService.getUserById(id))
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> getUsers() {
        return ResponseEntity.ok(ApiResponse.<List<UserResponseDto>>builder()
                .success(true)
                .data(userService.getUsers())
                .build());
    }

}
