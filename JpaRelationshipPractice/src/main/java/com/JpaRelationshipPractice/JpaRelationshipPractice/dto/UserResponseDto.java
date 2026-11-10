package com.JpaRelationshipPractice.JpaRelationshipPractice.dto;

import com.JpaRelationshipPractice.JpaRelationshipPractice.enums.Role;
import com.JpaRelationshipPractice.JpaRelationshipPractice.enums.UserStatus;

public record UserResponseDto(

        Long id,
        String email,
        Role role,
        UserStatus status,
        UserProfileDto profileDto
) {

    public record UserProfileDto(

            String firstName,
            String lastName,
            String phoNumber,
            String address
    ) {}
}
