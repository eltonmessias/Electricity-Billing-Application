package com.eltonmessias.Electricity_Billing.dto;

import com.eltonmessias.Electricity_Billing.model.Customer;

import java.time.LocalDateTime;

public record ConsumptionDTO(
        Long id,
        Long customer_id,
        Double consumptionInKwh,
        LocalDateTime readingDate,
        Double costPerUnit,
        Double totalCost
) {
}
