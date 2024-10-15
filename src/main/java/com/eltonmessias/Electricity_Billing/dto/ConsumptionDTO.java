package com.eltonmessias.Electricity_Billing.dto;

import com.eltonmessias.Electricity_Billing.model.Customer;

import java.time.LocalDateTime;

public record ConsumptionDTO(
        Long id,
        Customer customer,
        double consumption,
        LocalDateTime readingDate,
        double costPerUnit,
        double totalCost
) {
}
