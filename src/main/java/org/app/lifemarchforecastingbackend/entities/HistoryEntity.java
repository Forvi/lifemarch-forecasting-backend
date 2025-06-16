package org.app.lifemarchforecastingbackend.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * Сущность историй товара.
 * Необходим для отдельного хранения каждого загруженного товара в базе данных на длительный промежуток.
 *
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Schema(description = "Таблица с историческими данными, формируются при каждой загрузке файлов")
@Table(name = "History")
public class HistoryEntity {

    @Id
    @Schema(description = "Уникальный идентификатор товара в истории")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Наименование товара")
    @Size(min = 2, max = 50, message = "Название товара должно быть от 2 до 50 символов")
    @Column(nullable = false, name = "product_name")
    private String name;

    @Schema(description = "Дата запуска")
    @Column(nullable = false, name = "date")
    private Date date;

    @Schema(description = "Количество проданных товаров")
    @Column(nullable = false, name = "count_sales")
    private Integer countSales;

    @Schema(description = "Выручка от продажи товаров")
    @Column(nullable = false, name = "revenue")
    private BigDecimal revenue;

    @Schema(description = "Количество списанных товаров")
    @Column(nullable = false, name = "write_off_count")
    private Integer writeOffCount;
}
