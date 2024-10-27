package com.eltonmessias.Electricity_Billing.dto;

import lombok.Data;

@Data
public class StripeTokenDTO {
    public String cardNumber;
    public String expMonth;
    public String expYear;
    public String cvc;
    public String token;
    public String email;
    public boolean success;
}
