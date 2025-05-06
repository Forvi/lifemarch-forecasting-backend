package org.app.lifemarchforecastingbackend.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Сущность категории товара.
 * Необходим для фильтрации.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Schema(description = "Категория товара")
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @Schema(description = "Уникальный ID категории")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Наименование категории")
    @Size(min = 2, max = 50, message = "Название категории должно быть от 2 до 50 символов")
    @Column(unique = true, nullable = false, name = "category_name")
    private String name;
}
