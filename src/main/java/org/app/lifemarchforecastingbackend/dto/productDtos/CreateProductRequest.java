package org.app.lifemarchforecastingbackend.dto.productDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.app.lifemarchforecastingbackend.entities.CategoryEntity;

import java.math.BigDecimal;

public record CreateProductRequest(
        @NotBlank @NotEmpty @NotNull @Size(min = 2, max = 50)
        String name,

        @NotBlank @NotEmpty @NotNull
        Integer quantityBuy,

        @NotBlank @NotEmpty @NotNull
        BigDecimal costPrice,

        @NotNull
        CategoryEntity category
) {}
