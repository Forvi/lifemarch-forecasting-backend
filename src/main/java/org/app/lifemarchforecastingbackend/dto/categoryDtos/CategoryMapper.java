package org.app.lifemarchforecastingbackend.dto.categoryDtos;

import org.app.lifemarchforecastingbackend.entities.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(CategoryEntity entity);

    default CategoryEntity toEntity(CategoryDto dto) {
        if (dto == null) return null;

        CategoryEntity entity = new CategoryEntity();
        entity.setId(dto.id());
        entity.setName(dto.name());

        return entity;
    }
}
