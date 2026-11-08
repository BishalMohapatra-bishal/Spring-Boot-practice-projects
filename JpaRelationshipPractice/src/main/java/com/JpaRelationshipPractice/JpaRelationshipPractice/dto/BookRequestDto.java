package com.JpaRelationshipPractice.JpaRelationshipPractice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Set;

public record BookRequestDto(

        @NotBlank(message = "Title cannot be blank")
        String title,

        @NotBlank(message = "ISBN is required")
        @Size(min = 10, max = 13, message = "ISBN must be with in 10 to 13 character")
        String isbn,

        String publisher,

        LocalDate publishedDate,

        String genre,

        String language,


        @NotNull(message = "Total copies count is required")
        @Min(value = 1, message = "Total copies must be 1")
        Integer totalCopies,

        String description,

        @NotNull(message = "At least one author ID must be provided")
        Set<Long> authorIds


) {
}
