package org.app.lifemarchforecastingbackend.dto.productDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CreateProductRequest(
        @NotBlank @NotEmpty @Size(min = 2, max = 50) String productCode,
        String name,
        @NotBlank @NotEmpty @Size(min = 2, max = 50)
        Long categoryId
) { }
