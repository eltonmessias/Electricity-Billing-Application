package com.eltonmessias.Electricity_Billing.dto;

import lombok.Data;


public record PaymentInfoRequest(int amount, String currency, String receiptEmail) {
}
