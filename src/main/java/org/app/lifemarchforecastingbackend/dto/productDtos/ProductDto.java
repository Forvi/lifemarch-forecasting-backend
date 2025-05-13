package org.app.lifemarchforecastingbackend.dto.productDtos;

import org.app.lifemarchforecastingbackend.dto.categoryDtos.CategoryDto;

public record ProductDto(
        Long id,
        String name,
        CategoryDto categoryDto
) { }
