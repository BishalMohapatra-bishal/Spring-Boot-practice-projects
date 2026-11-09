package com.JpaRelationshipPractice.JpaRelationshipPractice.dto;

import com.JpaRelationshipPractice.JpaRelationshipPractice.enums.RecordStatus;

import java.time.LocalDate;

public record RecordsResponseDto(

        Long id,
        LocalDate borrowDate,
        LocalDate dueDate,
        LocalDate returnDate,
        RecordStatus status,
        String userEmail,
        String bookTitle

) {
}
