package com.JpaRelationshipPractice.JpaRelationshipPractice.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RecordRequestDto(

        @NotNull(message = "UserId is required")
        Long userId,

        @NotNull(message = "BookId is required")
        Long bookId,

        @NotNull(message = "Due date is required")
        LocalDate dueDate
) {
}
