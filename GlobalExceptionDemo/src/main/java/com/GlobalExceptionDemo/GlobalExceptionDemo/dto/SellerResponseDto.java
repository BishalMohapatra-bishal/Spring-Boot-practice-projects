package com.GlobalExceptionDemo.GlobalExceptionDemo.dto;

import java.util.List;

public record SellerResponseDto(
        Long id,
        String storeName,
        List<ProductSummeryDto> products
) {
}
