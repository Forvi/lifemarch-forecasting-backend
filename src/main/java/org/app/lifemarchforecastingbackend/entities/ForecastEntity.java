package org.app.lifemarchforecastingbackend.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 *
 * Сущность спрогнозированных товаров.
 * Необходим для хранения спрогнозированных товаров,
 * таблица связана с уникальным продуктом.
 * При новых генерациях спрогнозированного товара,
 * текущий будет перемещаться в историю (sales).
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Schema(description = "Спрогнозированные товары и необходимое количество закупки")
@Table(name = "forecasting")
public class ForecastEntity {

    @Id
    @Schema(description = "Уникальный ID прогноза")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_date")
    @FutureOrPresent(message = "Дата начала прогноза не может быть в прошлом")
    @Schema(description = "С какого дня нужен прогноз")
    private LocalDate fromDate;

    @Column(name = "to_date")
    @Future(message = "Дата окончания должна быть в будущем")
    @Schema(description = "По какой день нужен прогноз")
    private LocalDate toDate;

    @Column(name = "predicted_quantity")
    @Schema(description = "Спрогнозированное количество закупки")
    private int predictedQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @Schema(description = "Продукт для которого сделан прогноз")
    ProductEntity product;
}
