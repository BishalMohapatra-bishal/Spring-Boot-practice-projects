package com.JpaRelationshipPractice.JpaRelationshipPractice.dto;

import java.time.LocalDate;

public record AuthorResponseDto(

        Long id,
        String name,
        String biography,
        LocalDate birthDate
) {
}
