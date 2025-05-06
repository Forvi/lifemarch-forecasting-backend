package org.app.lifemarchforecastingbackend.services;

import lombok.RequiredArgsConstructor;
import org.app.lifemarchforecastingbackend.dto.categoryDtos.CategoryDto;
import org.app.lifemarchforecastingbackend.dto.categoryDtos.CategoryMapper;
import org.app.lifemarchforecastingbackend.entities.CategoryEntity;
import org.app.lifemarchforecastingbackend.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepo categoryRepo;

    @Qualifier("categoryMapperImpl")
    private final CategoryMapper mapper;

    public CategoryDto createCategory(String name) {
        CategoryEntity category = new CategoryEntity();
        category.setName(name);
        var savedCategory = categoryRepo.save(category);

        return mapper.toDto(savedCategory);
    }
}
