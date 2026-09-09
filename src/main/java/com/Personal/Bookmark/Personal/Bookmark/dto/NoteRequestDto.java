package com.Personal.Bookmark.Personal.Bookmark.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import java.util.Set;

public record NoteRequestDto(
        @NotBlank(message = "Title is required")
        String title,

        String content,

        @URL(message = "Must be a valid URL")
        String url,

        String categoryName,

        Set<String> tagNames
) {
}
