package com.eltonmessias.Electricity_Billing.dto;

public record PaymentInfoRequest(int amount, String currency, String receiptEmail) {
}
