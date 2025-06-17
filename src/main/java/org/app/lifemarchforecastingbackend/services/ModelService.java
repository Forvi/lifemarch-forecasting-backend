package org.app.lifemarchforecastingbackend.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.app.lifemarchforecastingbackend.dto.historyDto.CreateHistoryDto;
import org.app.lifemarchforecastingbackend.dto.historyDto.HistoryDto;
import org.app.lifemarchforecastingbackend.dto.productDto.ProductMapper;
import org.app.lifemarchforecastingbackend.repository.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class ModelService {

    private final ProductService productService;
    private final HistoryService historyService;

    // Поля json файла
    private static final String FIELD_CATEGORY = "Категория 2";
    private static final String FIELD_DISH = "Блюдо";
    private static final String FIELD_COST = "Себестоимость за единицу";
    private static final String FIELD_BUY_COUNT = "закупка";
    private static final  String FIELD_DATE = "Дата запуска";
    private static final  String FIELD_COUNT_SALES = "Продано количество";
    private static final  String FIELD_WRITE_OFF = "Кол-во списаний";
    private static final  String FIELD_REVENUE = "Выручка";
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
            BigDecimal costCount = getBigDecimalField(jsonObject, FIELD_COST);
            Integer buyCount = getIntegerField(jsonObject, FIELD_BUY_COUNT);
            String date = getStringField(jsonObject, FIELD_DATE);
            Integer countSales = getIntegerField(jsonObject, FIELD_COUNT_SALES);
            Integer writeOff = getIntegerField(jsonObject, FIELD_WRITE_OFF);
            BigDecimal revenue = getBigDecimalField(jsonObject, FIELD_REVENUE);

            CreateHistoryDto history = new CreateHistoryDto(dish, date, countSales, revenue, writeOff);

            productService.createProduct(dish, buyCount, costCount, category);
            historyService.createHistory(history);
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
    private BigDecimal getBigDecimalField(JsonObject jsonObject, String fieldName) {
        try {
            return jsonObject.get(fieldName).getAsBigDecimal();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Field '" + ModelService.FIELD_COST + "' is not a valid number");
        }
    }

    // Валдиация целочисленных полей в Json файле
    private Integer getIntegerField(JsonObject jsonObject, String fieldName) {
        try {
            return jsonObject.get(fieldName).getAsInt();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Field '" + fieldName + "' is not a valid integer");
        }
    }

}
