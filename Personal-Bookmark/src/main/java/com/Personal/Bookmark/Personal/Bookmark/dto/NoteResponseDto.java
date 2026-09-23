package com.Personal.Bookmark.Personal.Bookmark.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record NoteResponseDto(
        Long id,
        String title,
        String content,
        String url,
        boolean pinned,
        LocalDateTime createdAt,
        String category,
        Set<String> tags
) {
}
