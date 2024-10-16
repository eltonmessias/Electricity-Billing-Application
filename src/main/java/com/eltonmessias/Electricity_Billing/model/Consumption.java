package com.eltonmessias.Electricity_Billing.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Consumption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;
    Double consumptionInKwh;
    LocalDateTime readingDate;
    Double costPerUnit;
    Double totalCost;
}
