package com.chigolite.jobtracker_user_service.user.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String fullname;
    private String email;
    private String role;
    private LocalDateTime createdAt;

}
