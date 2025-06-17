package org.app.lifemarchforecastingbackend.dto.historyDto;

import org.app.lifemarchforecastingbackend.entities.HistoryEntity;
import org.mapstruct.Mapper;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface HistoryMapper {

    HistoryDto toDto(HistoryEntity entity);
    HistoryEntity toEntity(HistoryDto dto);

    CreateHistoryDto toCreateDto(HistoryEntity entity);

    HistoryEntity toCreateEntity(CreateHistoryDto dto);

    HistoryRevenueDto toRevenueDto(HistoryEntity entity);
    HistoryEntity toRevenueEntity(HistoryRevenueDto dto);

    HistoryCountSalesDto toCountSalesDto(HistoryEntity entity);
    HistoryEntity toCountSalesEntity(HistoryCountSalesDto dto);

    HistoryWriteOffDto toWriteOffDto(HistoryEntity entity);
    HistoryEntity toWriteOffEntity(HistoryWriteOffDto dto);
}
