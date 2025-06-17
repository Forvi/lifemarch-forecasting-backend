package org.app.lifemarchforecastingbackend.dto.historyDto;

import org.app.lifemarchforecastingbackend.entities.HistoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HistoryMapper {

    HistoryDto toDto(HistoryEntity entity);

    CreateHistoryDto toCreateDto(HistoryEntity entity);

    HistoryEntity toEntity(HistoryDto dto);

    HistoryEntity toCreateEntity(CreateHistoryDto dto);

}
