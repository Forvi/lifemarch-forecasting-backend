package org.app.lifemarchforecastingbackend.dto.productDto;

import org.app.lifemarchforecastingbackend.dto.categoryDtos.CategoryMapper;
import org.app.lifemarchforecastingbackend.entities.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface ProductMapper {
    ProductDto toDto(ProductEntity entity);
}
