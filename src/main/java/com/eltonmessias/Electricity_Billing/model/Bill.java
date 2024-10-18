package com.eltonmessias.Electricity_Billing.model;

import com.eltonmessias.Electricity_Billing.enums.BillStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "reading_id")
    private Reading reading;
    private double amountDue;
    private LocalDateTime issuedDate;
    private LocalDateTime dueDate;
    private BillStatus status;

}
