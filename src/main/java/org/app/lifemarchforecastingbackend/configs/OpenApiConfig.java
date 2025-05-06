package org.app.lifemarchforecastingbackend.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Класс конфигурации Swagger.
 * Swagger - инструмент для документирования и
 * тестирования API.
 *
 * @info - информация о API
 * @title - заголовок
 * @description - описание
 * @version - версия
 *
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Lifemarch forecasting application - backend",
                description = "API приложения для прогнозирования выручки магазина Жизньмарт",
                version = "1.0.0"
        )
)
public class OpenApiConfig {
    // Конфигурация Swagger
}
