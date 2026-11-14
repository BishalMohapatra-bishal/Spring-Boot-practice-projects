package com.JpaRelationshipPractice.JpaRelationshipPractice.service;

import com.JpaRelationshipPractice.JpaRelationshipPractice.dto.UserRegistrationRequestDto;
import com.JpaRelationshipPractice.JpaRelationshipPractice.dto.UserResponseDto;

import java.util.List;

public interface UserProfileService {
    UserResponseDto registerUser(UserRegistrationRequestDto dto);

    UserResponseDto updateUserProfile(Long id, UserRegistrationRequestDto requestDto);

    UserResponseDto getUserByEmail(String email);

    UserResponseDto getUserProfileById(Long id);

    List<UserResponseDto> getAllUserAndProfile();

    void deleteUserById(Long id);

    void deleteAllUsers();
}
