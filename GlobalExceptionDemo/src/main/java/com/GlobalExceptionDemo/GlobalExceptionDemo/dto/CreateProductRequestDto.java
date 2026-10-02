package com.GlobalExceptionDemo.GlobalExceptionDemo.dto;

public record CreateProductRequestDto(
        String name,
        Double price,
        Long sellerId
) {
}
