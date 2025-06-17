package org.app.lifemarchforecastingbackend.dto.historyDto;

import java.math.BigDecimal;

public record HistoryRevenueDto(
    String date,
    BigDecimal revenue
) { }
