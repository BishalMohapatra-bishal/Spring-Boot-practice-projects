package com.JpaRelationshipPractice.JpaRelationshipPractice.service;

import com.JpaRelationshipPractice.JpaRelationshipPractice.dto.UserRegistrationRequestDto;
import com.JpaRelationshipPractice.JpaRelationshipPractice.dto.UserResponseDto;

public interface UserProfileService {
    UserResponseDto registerUser(UserRegistrationRequestDto dto);

}
