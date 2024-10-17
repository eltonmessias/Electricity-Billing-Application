package com.eltonmessias.Electricity_Billing.dto;

import jakarta.persistence.Column;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

public record BillDTO(
        long id,
        long customerId,
        long readingId,

        double amountDue,
        LocalDateTime issuedDate,

        LocalDateTime dueDate,
        boolean paid
) { }
