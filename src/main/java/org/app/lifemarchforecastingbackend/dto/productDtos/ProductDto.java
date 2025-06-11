package org.app.lifemarchforecastingbackend.dto.productDtos;

import org.app.lifemarchforecastingbackend.entities.CategoryEntity;

import java.math.BigDecimal;

public record ProductDto(
    Long id,
    String name,
    Integer quantityBuy,
    BigDecimal costPrice,
    CategoryEntity category
) { }
