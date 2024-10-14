package com.eltonmessias.Electricity_Billing.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Consumer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private String address;
}
