package com.JpaRelationshipPractice.JpaRelationshipPractice.service.impl;

import com.JpaRelationshipPractice.JpaRelationshipPractice.dto.UserRegistrationRequestDto;
import com.JpaRelationshipPractice.JpaRelationshipPractice.dto.UserResponseDto;
import com.JpaRelationshipPractice.JpaRelationshipPractice.enums.Role;
import com.JpaRelationshipPractice.JpaRelationshipPractice.enums.UserStatus;
import com.JpaRelationshipPractice.JpaRelationshipPractice.exception.UserNotFoundException;
import com.JpaRelationshipPractice.JpaRelationshipPractice.model.User;
import com.JpaRelationshipPractice.JpaRelationshipPractice.model.UserProfile;
import com.JpaRelationshipPractice.JpaRelationshipPractice.repository.UserProfileRepository;
import com.JpaRelationshipPractice.JpaRelationshipPractice.repository.UserRepository;
import com.JpaRelationshipPractice.JpaRelationshipPractice.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileImpl implements UserProfileService {

    private final UserProfileRepository profileRepository;
    private final UserRepository repository;

    @Override
    public UserResponseDto registerUser(UserRegistrationRequestDto dto) {
        User newUser = User.builder()
                .email(dto.email())
                .password(dto.password())
                .role(Role.MEMBER)
                .status(UserStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .build();
//        newUser.setEmail(dto.email());
//        newUser.setPassword(dto.password());
//        newUser.setRole(Role.MEMBER);
//        newUser.setStatus(UserStatus.ACTIVE);
//        newUser.setCreatedAt(LocalDateTime.now());

        UserProfile profile = UserProfile.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .phoneNumber(dto.phoneNumber())
                .address(dto.address())
                .build();
//        profile.setFirstName(dto.firstName());
//        profile.setLastName(dto.lastName());
//        profile.setPhoneNumber(dto.phoneNumber());
//        profile.setAddress(dto.address());

        newUser.assignProfile(profile);
//        profileRepository.save(profile);
        repository.save(newUser);

        return entityToDto(newUser);
    }

    public UserResponseDto updateUserProfile(Long id, UserRegistrationRequestDto requestDto) {

        User existUser = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No user found with id: " + id));

        // Update User fields
        existUser.setEmail(requestDto.email());
        existUser.setPassword(requestDto.password()); // Remember to encode this later when you add security!

        // Update UserProfile fields using standard setters
        UserProfile profile = existUser.getProfile();
        if (profile != null) {
            profile.setFirstName(requestDto.firstName());
            profile.setLastName(requestDto.lastName());
            profile.setPhoneNumber(requestDto.phoneNumber());
            profile.setAddress(requestDto.address());
        } else {
            // Edge case safety: If somehow the user didn't have a profile, create one
            UserProfile newProfile = UserProfile.builder()
                    .firstName(requestDto.firstName())
                    .lastName(requestDto.lastName())
                    .phoneNumber(requestDto.phoneNumber())
                    .address(requestDto.address())
                    .build();
            existUser.assignProfile(newProfile);
        }

        // Save the updated user (Cascade will update the profile too)
        User savedUser = repository.save(existUser);

        return entityToDto(savedUser);
    }

    public UserResponseDto getUserProfileById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No user found with id : " + id));

        return entityToDto(user);
    }

    public List<UserResponseDto> getAllUserAndProfile() {

        List<User> allUsers = repository.findAll();

        return allUsers.stream().map(this::entityToDto).toList();
    }

    public void deleteUserById(Long id) {
        User deleteUser = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No user found with id: " + id));

        repository.delete(deleteUser);
    }

    public void deleteAllUsers() {
        repository.deleteAll();
    }
    private UserResponseDto entityToDto(User user) {
        // 1. Safely map the profile if it exists
        UserResponseDto.UserProfileDto profileDto = null;
        if (user.getProfile() != null) {
            profileDto = new UserResponseDto.UserProfileDto(
                    user.getProfile().getFirstName(),
                    user.getProfile().getLastName(),
                    user.getProfile().getPhoneNumber(),
                    user.getProfile().getAddress()
            );
        }
        // 2. Return the combined UserResponseDto
        return new UserResponseDto(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                user.getStatus(),
                profileDto
        );
    }
}