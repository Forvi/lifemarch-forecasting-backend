package org.app.lifemarchforecastingbackend.dto.historyDto;

import java.math.BigDecimal;

public record HistoryDto(
        Long id,
        String name,
        String date,
        Integer countSales,
        BigDecimal revenue,
        Integer writeOffCount
) { }
