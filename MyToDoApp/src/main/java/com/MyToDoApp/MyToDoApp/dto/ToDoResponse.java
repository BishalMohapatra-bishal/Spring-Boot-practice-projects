package com.MyToDoApp.MyToDoApp.dto;

import java.time.LocalDateTime;

public record ToDoResponse(
        Long id,
        String task,
        Boolean completed,
        LocalDateTime createdAt
) {
}