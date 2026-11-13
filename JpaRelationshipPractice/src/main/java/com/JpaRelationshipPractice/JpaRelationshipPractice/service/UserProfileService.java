package com.JpaRelationshipPractice.JpaRelationshipPractice.service;

import com.JpaRelationshipPractice.JpaRelationshipPractice.dto.UserRegistrationRequestDto;
import com.JpaRelationshipPractice.JpaRelationshipPractice.dto.UserResponseDto;

import java.util.List;

public interface UserProfileService {
    UserResponseDto registerUser(UserRegistrationRequestDto dto);

    UserResponseDto updateUserProfile(Long id, UserRegistrationRequestDto requestDto);

    UserResponseDto getUserProfileById(Long id);

    List<UserResponseDto> getAllUserAndProfile();

    public void deleteUserById(Long id);

    public void deleteAllUsers();
}
