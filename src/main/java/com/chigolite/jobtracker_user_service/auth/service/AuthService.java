package com.chigolite.jobtracker_user_service.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chigolite.jobtracker_user_service.auth.dtos.AuthResponseDto;
import com.chigolite.jobtracker_user_service.auth.dtos.LoginDto;
import com.chigolite.jobtracker_user_service.auth.dtos.RegisterDto;
import com.chigolite.jobtracker_user_service.common.exceptionHandler.DuplicationException;
import com.chigolite.jobtracker_user_service.common.exceptionHandler.ResourceNotFound;
import com.chigolite.jobtracker_user_service.security.JwtUtil;
import com.chigolite.jobtracker_user_service.user.dto.UserResponseDto;
import com.chigolite.jobtracker_user_service.user.entity.Role;
import com.chigolite.jobtracker_user_service.user.entity.User;
import com.chigolite.jobtracker_user_service.user.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public UserResponseDto register(RegisterDto request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicationException("Email already exists");
        }
        User user = new User();
        user.setFullname(request.getFullname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        User saved = userRepository.save(user);
        return mapToResponse(saved);
    }

    public AuthResponseDto login(LoginDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFound("User not found"));

        String token = jwtUtil.generateToken(user);
        return new AuthResponseDto(token, user.getEmail(), user.getRole().name());
    }

    private UserResponseDto mapToResponse(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getFullname(),
                user.getEmail(),
                user.getRole().name(),
                user.getCreatedAt());
    }
}
