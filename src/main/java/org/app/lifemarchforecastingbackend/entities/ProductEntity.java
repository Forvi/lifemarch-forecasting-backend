package org.app.lifemarchforecastingbackend.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.app.lifemarchforecastingbackend.dto.categoryDtos.CategoryDto;

import java.math.BigDecimal;

/**
 *
 * Сущность товара.
 * Необходим для отдельного хранения каждого товара в базе данных.
 *
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Schema(description = "Категория товара")
@Table(name = "products")
public class ProductEntity {

    @Id
    @Schema(description = "Уникальный ID продукта")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Наименование продукта")
    @Size(min = 2, max = 50, message = "Название продукта должно быть от 2 до 50 символов")
    @Column(unique = false, nullable = false, name = "product_name")
    private String name;

    @Schema(description = "Спрогнозированное количество для покупки")
    @Column(nullable = false, name = "quantity_buy")
    private Integer quantityBuy;

    @Schema(description = "Себестоимость товара")
    @Column(nullable = false, name = "cost_price")
    private BigDecimal costPrice;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @Schema(description = "Категория продукта")
    private CategoryEntity category;

}
