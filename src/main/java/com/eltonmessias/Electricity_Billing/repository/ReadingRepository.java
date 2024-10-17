package com.eltonmessias.Electricity_Billing.repository;

import com.eltonmessias.Electricity_Billing.model.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingRepository extends JpaRepository<Reading, Long> {
}
