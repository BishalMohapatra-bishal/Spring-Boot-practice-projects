package com.GlobalExceptionDemo.GlobalExceptionDemo.dto;

public record AddToCartRequestDto(
        Long productId,
        Integer quantity
) {
}
