package org.app.lifemarchforecastingbackend.dto.historyDto;

import java.math.BigDecimal;
import java.util.Date;

public record CreateHistoryDto(
        String name,
        String date,
        Integer countSales,
        BigDecimal revenue,
        Integer writeOffCount
) { }
