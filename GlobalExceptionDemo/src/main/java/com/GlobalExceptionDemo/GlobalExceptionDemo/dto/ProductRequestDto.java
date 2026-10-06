package com.GlobalExceptionDemo.GlobalExceptionDemo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductRequestDto(

        @NotBlank(message = "Name is required")
        @Size(min = 5, max = 50, message = "The name should in between 5 to 50")
        String name,

        @NotNull(message = "The price is required")
        Double price,

        @NotNull(message = "Seller id is required")
        Long sellerId

) {
}
