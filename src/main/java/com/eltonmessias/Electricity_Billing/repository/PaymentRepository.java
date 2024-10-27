package com.eltonmessias.Electricity_Billing.repository;

import com.eltonmessias.Electricity_Billing.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByEmail(String email);
}
