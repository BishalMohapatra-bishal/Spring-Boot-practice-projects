package com.JpaRelationshipPractice.JpaRelationshipPractice.service.impl;

import com.JpaRelationshipPractice.JpaRelationshipPractice.dto.UserRegistrationRequestDto;
import com.JpaRelationshipPractice.JpaRelationshipPractice.dto.UserResponseDto;
import com.JpaRelationshipPractice.JpaRelationshipPractice.enums.Role;
import com.JpaRelationshipPractice.JpaRelationshipPractice.enums.UserStatus;
import com.JpaRelationshipPractice.JpaRelationshipPractice.model.User;
import com.JpaRelationshipPractice.JpaRelationshipPractice.model.UserProfile;
import com.JpaRelationshipPractice.JpaRelationshipPractice.repository.UserProfileRepository;
import com.JpaRelationshipPractice.JpaRelationshipPractice.repository.UserRepository;
import com.JpaRelationshipPractice.JpaRelationshipPractice.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
@RequiredArgsConstructor
public class UserProfileImpl implements UserProfileService {

    private final UserProfileRepository profileRepository;
    private final UserRepository repository;

    @Override
    public UserResponseDto registerUser(UserRegistrationRequestDto dto) {
        User newUser = new User();
        newUser.setEmail(dto.email());
        newUser.setPassword(dto.password());
        newUser.setRole(Role.MEMBER);
        newUser.setStatus(UserStatus.ACTIVE);
        newUser.setCreatedAt(LocalDateTime.now());

        UserProfile profile = new UserProfile();
        profile.setFirstName(dto.firstName());
        profile.setLastName(dto.lastName());
        profile.setPhoneNumber(dto.phoneNumber());
        profile.setAddress(dto.address());

        newUser.assignProfile(profile);
//        profileRepository.save(profile);
        repository.save(newUser);

        return new UserResponseDto(newUser.getId(),
                                   newUser.getEmail(),
                                   newUser.getRole(),
                                   newUser.getStatus(),

                new UserResponseDto.UserProfileDto(profile.getFirstName(),
                                                   profile.getLastName(),
                                                   profile.getPhoneNumber(),
                                                   profile.getAddress()));

    }
}
