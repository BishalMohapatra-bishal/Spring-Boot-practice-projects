package com.GlobalExceptionDemo.GlobalExceptionDemo.dto;

public record CustomerResponseDto(
        Long id,
        String name,
        String email,
        String phoneNumber
) {
}
