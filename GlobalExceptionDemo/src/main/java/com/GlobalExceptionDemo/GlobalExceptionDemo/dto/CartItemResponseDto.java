package com.GlobalExceptionDemo.GlobalExceptionDemo.dto;

public record CartItemResponseDto(
        Long id,
        Integer quantity,
        ProductSummeryDto product
) {
}
