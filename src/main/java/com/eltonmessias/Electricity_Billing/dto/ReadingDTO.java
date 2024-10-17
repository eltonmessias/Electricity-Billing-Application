package com.eltonmessias.Electricity_Billing.dto;

import java.time.LocalDateTime;

public record ReadingDTO(
        Long id,
        Long customer_id,
        Double consumptionInKwh,
        LocalDateTime readingDate,
        Double costPerUnit,
        Double totalCost
) {
}
