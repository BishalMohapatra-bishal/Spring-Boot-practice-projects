package com.GlobalExceptionDemo.GlobalExceptionDemo.dto;

import jakarta.validation.constraints.NotBlank;

public record CustomerRequestDto(

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "Phone number is required")
        String phoneNumber
) {
}
