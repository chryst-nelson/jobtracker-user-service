package com.chigolite.jobtracker_user_service.user.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chigolite.jobtracker_user_service.common.exceptionHandler.ResourceNotFound;
import com.chigolite.jobtracker_user_service.user.dto.UserResponseDto;
import com.chigolite.jobtracker_user_service.user.entity.User;
import com.chigolite.jobtracker_user_service.user.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
    }

    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("User not found"));
        return new UserResponseDto(
                user.getId(),
                user.getFullname(),
                user.getEmail(),
                user.getRole().name(),
                user.getCreatedAt());
    }

    public List<UserResponseDto> getUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getFullname(),
                        user.getEmail(),
                        user.getRole().name(),
                        user.getCreatedAt()))
                .toList();
    }
}
