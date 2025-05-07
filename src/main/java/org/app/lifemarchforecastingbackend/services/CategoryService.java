package org.app.lifemarchforecastingbackend.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.app.lifemarchforecastingbackend.dto.categoryDtos.*;
import org.app.lifemarchforecastingbackend.entities.CategoryEntity;
import org.app.lifemarchforecastingbackend.exceptions.OperationError;
import org.app.lifemarchforecastingbackend.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepo categoryRepo;

    @Qualifier("categoryMapperImpl")
    private final CategoryMapper mapper;


    public CategoryDto createCategory(String name) {
        try {
            log.debug("Creating new category: {}...", name);

            Objects.requireNonNull(name, "Name cannot be null");
            if (name.isBlank())
                throw new OperationError("Name cannot bee empty");

            CategoryEntity category = new CategoryEntity();
            category.setName(name);
            CategoryEntity savedCategory = categoryRepo.save(category);

            log.info("Created category : {}", name);
            return mapper.toDto(savedCategory);
        } catch (OperationError e) {
            log.error("Failed to create category: {}", name, e);
            throw new OperationError("Error: " + e.getMessage());
        }
    }

    public List<CategoryDto> getAllCategories() {
        try {
            log.debug("Getting all categories...");

            List<CategoryDto> category = categoryRepo.findAll()
                    .stream()
                    .map(mapper::toDto)
                    .toList();

            log.info("{} categories found", category.size());

            return category;
        } catch (OperationError e) {
            log.error("Failed to get categories");
            throw new OperationError("Error: " + e.getMessage());
        }

    }
}
