package org.app.lifemarchforecastingbackend.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.app.lifemarchforecastingbackend.services.ModelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Связь с ML моделью", description = "Контроллер обрабатывает все запросы связанные с ML моделью")
@RequestMapping("/api/model")
public class ModelController {

    private final ModelService modelService;

    @PostMapping("/create")
    @Operation(summary = "Получить JSON от модели и создать на его основе записи в БД")
    public ResponseEntity<?> createModelAnswers(@RequestBody String json) {
        modelService.createModelAnswers(json);
        return ResponseEntity.ok("Data successfully received and created in the database");
    }

}
