package com.GlobalExceptionDemo.GlobalExceptionDemo.dto;

import java.util.List;

public record CartResponseDto(
        String id,
        Long customerId,
        String customerName,
        List<CartItemResponseDto> cartItems
) {
}
