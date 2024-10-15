package com.eltonmessias.Electricity_Billing.repository;

import com.eltonmessias.Electricity_Billing.model.Consumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumptionRepository extends JpaRepository<Consumption, Long> {
}
