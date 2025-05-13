package org.app.lifemarchforecastingbackend.dto.productDtos;

import org.app.lifemarchforecastingbackend.entities.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto(ProductEntity entity);
}
