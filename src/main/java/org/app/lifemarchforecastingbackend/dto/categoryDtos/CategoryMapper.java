package org.app.lifemarchforecastingbackend.dto.categoryDtos;

import org.app.lifemarchforecastingbackend.entities.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(CategoryEntity entity);
}
