package org.app.lifemarchforecastingbackend.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.app.lifemarchforecastingbackend.dto.historyDto.CreateHistoryDto;
import org.app.lifemarchforecastingbackend.dto.historyDto.HistoryDto;
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
    public ResponseEntity<Void> createProductInHistory(@RequestBody CreateHistoryDto request) {
        historyService.createHistory(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getall")
    public ResponseEntity<List<HistoryDto>> getAllProductsFromHistory() {
        return ResponseEntity.ok(historyService.getAllProductsFromHistory());
    }

    @GetMapping("/getallbyname/{name}")
    public ResponseEntity<List<HistoryDto>> getAllByName(@PathVariable String name) {
        return ResponseEntity.ok(historyService.getByName(name));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        historyService.deleteProductFromHistory(id);
        return ResponseEntity.ok().build();
    }
}
