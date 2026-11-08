package com.JpaRelationshipPractice.JpaRelationshipPractice.dto;

import java.time.LocalDate;
import java.util.Set;

public record BookResponseDto(
         Long id,
         String title,
         String isbn,
         String publisher,
         LocalDate publishedDate,
         String genre,
         String language,
         Integer totalCopies,
         Integer availableCopies,
         String description,
         Set<AuthorResponseDto>authors
) {
}
