package org.app.lifemarchforecastingbackend.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.app.lifemarchforecastingbackend.dto.productDtos.ProductDto;
import org.app.lifemarchforecastingbackend.services.ModelService;
import org.app.lifemarchforecastingbackend.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@Tag(name = "Товары", description = "Обработка действий с товарами")
@RequestMapping("/api/product")
public class ProductController {

    private final ModelService modelService;
    private final ProductService productService;

    @PostMapping("/create")
    @Operation(
            summary = "Создать товар",
            description = "Позволяет получить JSON от модели и создать на его основе записи в БД")
    public ResponseEntity<?> createModelAnswers(@RequestBody String json) {
        modelService.createModelAnswers(json);
        return ResponseEntity.ok("Data successfully received and created in the database");
    }

    @GetMapping("/getall")
    @Operation(
            summary = "Получить все товары",
            description = "Позволяет получить все товары")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/getsubstring")
    @Operation(
            summary = "Получить товары по части имени",
            description = "Позволяет получить все товары по подстроке названия товара")
    public ResponseEntity<List<ProductDto>> getProductsBySubstringName(String name) {
        return ResponseEntity.ok(productService.getProductByNameSubstring(name));
    }

    @GetMapping("/getbycategories")
    @Operation(
            summary = "Получить товар по категории",
            description = "Позволяет получить все товары с указанными в списке категориями")
    public ResponseEntity<List<ProductDto>> getProductsByCategories(@RequestParam List<String> categories) {
        return ResponseEntity.ok(productService.getProductsByCategory(categories));
    }

    @GetMapping("/getbyid")
    @Operation(
            summary = "Получить товар по ID",
            description = "Позволяет получить товар по уникальному идентификатору")
    public ResponseEntity<ProductDto> getProductsById(Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @DeleteMapping("/delete")
    @Operation(
            summary = "Получить все товары",
            description = "Позволяет получить все товары")
    public ResponseEntity<Void> deleteProductById(Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok().build();
    }

}
