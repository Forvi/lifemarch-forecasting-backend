package org.app.lifemarchforecastingbackend.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.app.lifemarchforecastingbackend.dto.categoryDtos.*;
import org.app.lifemarchforecastingbackend.entities.CategoryEntity;
import org.app.lifemarchforecastingbackend.exceptions.NotFoundException;
import org.app.lifemarchforecastingbackend.exceptions.OperationErrorException;
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
                throw new OperationErrorException("Name cannot bee empty");

            CategoryEntity category = new CategoryEntity();
            category.setName(name);
            CategoryEntity savedCategory = categoryRepo.save(category);

            log.info("Created category : {}", name);
            return mapper.toDto(savedCategory);
        } catch (OperationErrorException e) {
            log.error("Failed to create category: {}", name, e);
            throw new OperationErrorException("Error: " + e.getMessage());
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
        } catch (OperationErrorException e) {
            log.error("Failed to get categories");
            throw new OperationErrorException("Error: " + e.getMessage());
        }
    }

    public CategoryDto getCategoryById(Long id) {
        try {
            log.debug("Getting category with id: {}...", id);

            CategoryEntity category = categoryRepo
                    .findById(id)
                    .orElseThrow(() -> new NotFoundException("Category not found"));

            log.info("Category {} found", category.getName());

            return mapper.toDto(category);
        } catch (OperationErrorException e) {
            log.error("Failed to get category with id: {}", id, e);
            throw new OperationErrorException("Error: " + e.getMessage());
        }
    }

    public List<CategoryDto> getCategoryByName(String name) {
        try {
            log.debug("Getting category with name: {}...", name);

            List<CategoryDto> category = categoryRepo.findByNameContainingIgnoreCase(name)
                    .stream()
                    .map(mapper::toDto)
                    .toList();

            log.info("Found {} categories by {}", category.size(), name);

            return category;
        } catch (OperationErrorException e) {
            log.error("Failed to get category with id: {}", name, e);
            throw new OperationErrorException("Error: " + e.getMessage());
        }
    }

    public void deleteCategoryById(Long id) {
        log.debug("Deleting category with id: {}...", id);

        CategoryEntity category = categoryRepo
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));

        categoryRepo.delete(category);
        log.info("Deleted category: {}", category.getName());
    }
}
