package org.app.lifemarchforecastingbackend.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.app.lifemarchforecastingbackend.dto.categoryDtos.*;
import org.app.lifemarchforecastingbackend.entities.CategoryEntity;
import org.app.lifemarchforecastingbackend.exceptions.NotFoundException;
import org.app.lifemarchforecastingbackend.exceptions.OperationErrorException;
import org.app.lifemarchforecastingbackend.repository.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepo categoryRepo;

//    @Qualifier("categoryMapperImpl")
    private final CategoryMapper mapper;

    /**
     * Создать категорию
     * */
    public CategoryDto createCategory(String name) {
        try {
            log.debug("Creating new category: {}...", name);

            log.info("Validity check...");
            Objects.requireNonNull(name, "Name cannot be null");

            if (name.isBlank())
                throw new OperationErrorException("Name cannot be empty");
            log.info("The data is valid");

            CategoryEntity existingCategory = categoryRepo.findByName(name);
            if (existingCategory != null) {

                log.info("Category {} already exists", existingCategory);
                return mapper.toDto(existingCategory);
            }

            CategoryEntity category = new CategoryEntity();
            category.setName(name);
            CategoryEntity savedCategory = categoryRepo.save(category);

            log.info("Created category: {}", name);
            return mapper.toDto(savedCategory);
        } catch (OperationErrorException e) {
            log.error("Failed to create category: {}", name, e);
            throw e;
        }
    }

    /**
     * Получить все категории
     * */
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

    /**
     * Получить категорию по ID
     * */
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

    /**
     * Получить категорию по имени
     * */
    public CategoryDto getCategoryByName(String name) {
        try {
            log.debug("Getting category with name: {}...", name);

            CategoryEntity category = categoryRepo.findByName(name);

            log.info("Found category: {}", name);

            return mapper.toDto(category);
        } catch (OperationErrorException e) {
            log.error("Failed to get category with name: {}", name, e);
            throw new OperationErrorException("Error: " + e.getMessage());
        }
    }

    /**
     * Получить категорию по подстроке имени
     * */
    public List<CategoryDto> getCategoryByNameSubstring(String name) {
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

    /**
     * Удалить категорию по ID
     * */
    public void deleteCategoryById(Long id) {
        log.debug("Deleting category with id: {}...", id);

        CategoryEntity category = categoryRepo
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));

        categoryRepo.delete(category);
        log.info("Deleted category: {}", category.getName());
    }
}
