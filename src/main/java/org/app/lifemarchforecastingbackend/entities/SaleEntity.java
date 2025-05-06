package org.app.lifemarchforecastingbackend.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 *
 * Сущность продажи.
 * Необходим для хранения каждой продажи,
 * таблица связана с уникальным продуктом.
 * Фактически это история продаж.
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Schema(description = "Категория товара")
@Table(name = "sales")
public class SaleEntity {

    @Id
    @Schema(description = "Уникальный ID продажи")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Внешний ID продажи из Excel-таблицы")
    private Long externalId;

    @Column(name = "sale_date")
    @Schema(description = "Дата продажи")
    private LocalDate saleDate;

    @Column(name = "quantity")
    @Schema(description = "Проданное количество")
    private int quantity;

    @Column(name = "price")
    @Schema(description = "Стоимость продукта")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @Schema(description = "Проданный продукт")
    private ProductEntity product;
}
