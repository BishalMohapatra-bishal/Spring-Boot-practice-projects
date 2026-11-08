package com.JpaRelationshipPractice.JpaRelationshipPractice.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record AuthorRequestDto(

        @NotBlank(message = "Name cannot be blank")
        String name,

        String biography,

        LocalDate birthDate
) {
}
