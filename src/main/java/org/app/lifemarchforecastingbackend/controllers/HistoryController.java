package org.app.lifemarchforecastingbackend.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.app.lifemarchforecastingbackend.dto.historyDto.*;
import org.app.lifemarchforecastingbackend.services.HistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "История", description = "Методы работы с историей товаров")
@RequestMapping("/api/history")
public class HistoryController {

    private final HistoryService historyService;

    @PostMapping("/create")
    @Operation(
            summary = "Создать товар в истории",
            description = "Создать товар в таблице истории")
    public ResponseEntity<Void> createProductInHistory(@RequestBody CreateHistoryDto request) {
        historyService.createHistory(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getall")
    @Operation(
            summary = "Получить все товары",
            description = "Получить все товары из таблицы истории")
    public ResponseEntity<List<HistoryDto>> getAllProductsFromHistory() {
        return ResponseEntity.ok(historyService.getAllProductsFromHistory());
    }

    @GetMapping("/getallbyname/{name}")
    @Operation(
            summary = "Получить все товары по имени",
            description = "Получить все товары по конкретному имени имени, указанным в запросе")
    public ResponseEntity<List<HistoryDto>> getAllByName(@PathVariable String name) {
        return ResponseEntity.ok(historyService.getByName(name));
    }

    @GetMapping("/getrevenue/{name}")
    @Operation(
            summary = "Получить список выручки товара",
            description = "Метод возвращает список типа [дата, выручка]")
    public ResponseEntity<List<HistoryRevenueDto>> getRevenueByNameFromHistory(@PathVariable String name) {
        return ResponseEntity.ok(historyService.getRevenueByNameFromHistory(name));
    }

    @GetMapping("/getcountsales/{name}")
    @Operation(
            summary = "Получить список количества проданного товара",
            description = "Метод возвращает список типа [дата, количество]")
    public ResponseEntity<List<HistoryCountSalesDto>> getCountSalesByNameFromHistory(@PathVariable String name) {
        return ResponseEntity.ok(historyService.getCountSalesByNameFromHistory(name));
    }

    @GetMapping("/getwriteoff/{name}")
    @Operation(
            summary = "Получить список количества списаний товара",
            description = "Метод возвращает список типа [дата, списания]")
    public ResponseEntity<List<HistoryWriteOffDto>> getWriteOffByNameFromHistory(@PathVariable String name) {
        return ResponseEntity.ok(historyService.getWriteOffByNameFromHistory(name));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Удалить товар",
            description = "Метод удаляет товар по заданному в запросе ID")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        historyService.deleteProductFromHistory(id);
        return ResponseEntity.ok().build();
    }
}
