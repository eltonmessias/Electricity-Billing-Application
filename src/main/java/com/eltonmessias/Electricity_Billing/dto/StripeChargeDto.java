package com.eltonmessias.Electricity_Billing.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class StripeChargeDto {
    private String stripeToken;
    private String email;
    private Double amount;
    private boolean success;
    private String message;
    public String chargeId;
    private Map<String, Object> additionalInfo = new HashMap<>();
}
