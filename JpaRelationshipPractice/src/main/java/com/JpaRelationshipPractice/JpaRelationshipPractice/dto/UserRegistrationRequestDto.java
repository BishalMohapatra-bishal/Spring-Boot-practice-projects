package com.JpaRelationshipPractice.JpaRelationshipPractice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegistrationRequestDto(

        @Email(message = "Invalid email format")
        @NotBlank(message = "Email can not be blank")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "password must be six letter long")
        String password,

        @NotBlank(message = "Firstname cannot be blank")
        String firstName,

        @NotBlank(message = "Lastname cannot be blank")
        String lastName,

        String phoneNumber,

        String address
) {
}
