package com.ErrorHandlingWithProblemDetails.ProblemDetails.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EmployeeRequestDto(

        @NotBlank(message = "Name cannot be blank")
        @Size(min = 5, max = 50, message = "The name must be in between 5 to 50")
        String name,

        @NotBlank(message = "Designation is needed")
        String designation
) {
}
