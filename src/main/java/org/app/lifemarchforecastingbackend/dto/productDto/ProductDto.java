package org.app.lifemarchforecastingbackend.dto.productDto;

import org.app.lifemarchforecastingbackend.dto.categoryDtos.CategoryDto;

import java.math.BigDecimal;

public record ProductDto(
    Long id,
    String name,
    Integer quantityBuy,
    BigDecimal costPrice,
    CategoryDto category
) { }
