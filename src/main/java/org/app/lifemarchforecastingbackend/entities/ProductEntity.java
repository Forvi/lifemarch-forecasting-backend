package org.app.lifemarchforecastingbackend.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 *
 * Сущность товара.
 * Необходим для отдельного хранения каждого товара
 * в базе данных.
 *
 */

@Data
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
    @Column(unique = true, nullable = false, name = "product_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @Schema(description = "Категория продукта")
    private CategoryEntity category;
}
