package org.app.lifemarchforecastingbackend.dto.categoryDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCategoryRequest(
        @NotBlank @Size(min = 2, max = 50)
        String name
) {}
