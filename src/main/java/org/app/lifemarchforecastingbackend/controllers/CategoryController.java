package org.app.lifemarchforecastingbackend.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.app.lifemarchforecastingbackend.dto.categoryDtos.CategoryDto;
import org.app.lifemarchforecastingbackend.dto.categoryDtos.CreateCategoryRequest;
import org.app.lifemarchforecastingbackend.entities.CategoryEntity;
import org.app.lifemarchforecastingbackend.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Tag(name = "Категории", description = "Все необходимые действия с категориями товаров")
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @Tag(name = "Создание категории", description = "Создает категорию с переданным названием")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoryDto> createCategories(@RequestBody CreateCategoryRequest request) {
        var result = categoryService.createCategory(request.name());
        return ResponseEntity.ok(result);
    }
}
