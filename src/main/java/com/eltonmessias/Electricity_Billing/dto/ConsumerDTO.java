package com.eltonmessias.Electricity_Billing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ConsumerDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,
        String firstName,
        String lastName,
        String email,
        String address) {
}
