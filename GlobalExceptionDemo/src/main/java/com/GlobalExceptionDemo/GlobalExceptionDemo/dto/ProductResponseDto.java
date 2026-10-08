package com.GlobalExceptionDemo.GlobalExceptionDemo.dto;

public record ProductResponseDto(
        Long id,
        String name,
        Double price,
        SellerSummaryDto seller
) {
}
