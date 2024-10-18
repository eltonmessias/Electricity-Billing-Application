package com.eltonmessias.Electricity_Billing.repository;

import com.eltonmessias.Electricity_Billing.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByCustomerId(long id);
}
