package org.app.lifemarchforecastingbackend.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.app.lifemarchforecastingbackend.dto.productDto.ProductMapper;
import org.app.lifemarchforecastingbackend.repository.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class ModelService {

    private final ProductService productService;
    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    // Поля json файла
    private static final String FIELD_CATEGORY = "Категория 2";
    private static final String FIELD_DISH = "Блюдо";
    private static final String FIELD_COST = "Себестоимость за единицу";
    private static final String FIELD_BUY_COUNT = "закупка";
    // ____

    /**
     * Метод обрабатывает JSON и создает записи в БД на его основе
     */
    @Transactional
    public void createModelAnswers(String jsonFile) {

        log.debug("Start created product...");

        if (jsonFile == null || jsonFile.isBlank()) {
            throw new IllegalArgumentException("JSON file empty or null");
        }

        try {
            JsonArray jsonArray = JsonParser.parseString(jsonFile).getAsJsonArray();

            for (var e : jsonArray) {
                processFields(e.getAsJsonObject());
            }

            log.info("Successfully created {} products", jsonArray.size());
        } catch (JsonParseException e) {
            throw new IllegalArgumentException("Invalid JSON format", e);
        } catch (Exception e) {
            log.error("Failed to process JSON file", e);
            throw new RuntimeException("Internal error while processing JSON", e);
        }
    }

    // Обработка полей в Json файле
    private void processFields(JsonObject jsonObject) {
        try {
            String category = getStringField(jsonObject, FIELD_CATEGORY);
            String dish = getStringField(jsonObject, FIELD_DISH);
            BigDecimal costCount = getBigDecimalField(jsonObject);
            Integer buyCount = getIntegerField(jsonObject);

            productService.createProduct(dish, buyCount, costCount, category);
            log.debug("Created product: {}", dish);

        } catch (IllegalArgumentException e) {
            log.warn("Skipping invalid product data: {}", e.getMessage());
        }
    }

    // Валдиация строковых полей в Json файле
    private String getStringField(JsonObject jsonObject, String fieldName) {
        if (!jsonObject.has(fieldName)) {
            throw new IllegalArgumentException("Field " + fieldName + " is missing");
        }

        return jsonObject.get(fieldName).getAsString();
    }

    // Валидация Decimal полей в Json файле
    private BigDecimal getBigDecimalField(JsonObject jsonObject) {
        try {
            return jsonObject.get(FIELD_COST).getAsBigDecimal();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Field '" + ModelService.FIELD_COST + "' is not a valid number");
        }
    }

    // Валдиация целочисленных полей в Json файле
    private Integer getIntegerField(JsonObject jsonObject) {
        try {
            return jsonObject.get(FIELD_BUY_COUNT).getAsInt();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Field '" + ModelService.FIELD_BUY_COUNT + "' is not a valid integer");
        }
    }

}
