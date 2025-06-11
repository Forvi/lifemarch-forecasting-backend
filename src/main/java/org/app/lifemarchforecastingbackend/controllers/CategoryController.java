package org.app.lifemarchforecastingbackend.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.app.lifemarchforecastingbackend.dto.categoryDtos.CategoryDto;
import org.app.lifemarchforecastingbackend.dto.categoryDtos.CreateCategoryRequest;
import org.app.lifemarchforecastingbackend.exceptions.OperationErrorException;
import org.app.lifemarchforecastingbackend.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Категории", description = "Все необходимые действия с категориями товаров")
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    @Operation(summary = "Создать категорию")
    public ResponseEntity<CategoryDto> createCategories(
            @RequestBody CreateCategoryRequest request) throws OperationErrorException {
        var result = categoryService.createCategory(request.name());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/findall")
    @Operation(
            summary = "Получить все категории",
            description = "Позволяет вернуть абсолютно все категории, которые есть в БД")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        var result = categoryService.getAllCategories();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/id/{id}")
    @Operation(
            summary = "Найти категории по ID",
            description = "Позволяет вернуть категорию по конкретному ID")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) throws OperationErrorException {
        var result = categoryService.getCategoryById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/name/{name}")
    @Operation(
            summary = "Получить категории по названию",
            description = "Позволяет вернуть все категории с указанной подстрокой")
    public ResponseEntity<List<CategoryDto>> getCategoryByName(@PathVariable String name)
            throws OperationErrorException {
        var result = categoryService.getCategoryByNameSubstring(name);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Удалить категорию по ID",
            description = "Позволяет удалить категории с указанным ID")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok().build();
    }

}
