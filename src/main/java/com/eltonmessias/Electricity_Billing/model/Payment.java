package com.eltonmessias.Electricity_Billing.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "payment")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "amount")
    private double amount;
}
