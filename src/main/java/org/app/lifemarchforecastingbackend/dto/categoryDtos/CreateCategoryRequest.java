package org.app.lifemarchforecastingbackend.dto.categoryDtos;

import jakarta.validation.constraints.*;

public record CreateCategoryRequest(
        @NotBlank @NotEmpty @Size(min = 2, max = 50)
        String name
) {}
