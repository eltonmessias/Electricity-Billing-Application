package com.eltonmessias.Electricity_Billing.service;

import com.eltonmessias.Electricity_Billing.dto.BillDTO;
import com.eltonmessias.Electricity_Billing.enums.BillStatus;
import com.eltonmessias.Electricity_Billing.exception.ResourceNotFoundException;
import com.eltonmessias.Electricity_Billing.model.Bill;
import com.eltonmessias.Electricity_Billing.model.Reading;
import com.eltonmessias.Electricity_Billing.repository.BillRepository;
import com.eltonmessias.Electricity_Billing.repository.CustomerRepository;
import com.eltonmessias.Electricity_Billing.repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ReadingRepository readingRepository;

    private BillDTO convertBillToDTO(Bill bill) {
        return  new BillDTO(
                bill.getId(),
                bill.getCustomer().getId(),
                bill.getReading().getId(),
                bill.getAmountDue(),
                bill.getDueDate(),
                bill.getIssuedDate(),
                bill.getStatus()

        );
    }

    public Bill generateBillFromReading(Reading reading) {
        Bill bill = new Bill();
        bill.setCustomer(reading.getCustomer());
        bill.setReading(reading);

        double totalCost = reading.getConsumptionInKwh() * reading.getCostPerUnit();
        double tax = totalCost * 0.10;
        double amountDue = totalCost + tax;

        bill.setIssuedDate(LocalDateTime.now());
        bill.setDueDate(bill.getIssuedDate().plusDays(30));

        long daysLate = ChronoUnit.DAYS.between(bill.getDueDate(), LocalDateTime.now());
        if (daysLate > 0) {
            double lateFee = amountDue * 0.01 * daysLate;
            amountDue += lateFee;
        }

        bill.setAmountDue(amountDue);
        bill.setStatus(BillStatus.PENDING);

        return bill;
    }

    public BillDTO createBill(long readingId) {
        Reading reading = readingRepository.findById(readingId).orElseThrow(() -> new ResourceNotFoundException("reading not found"));
        Bill bill = generateBillFromReading(reading);
        billRepository.save(bill);
        return convertBillToDTO(bill);
    }

    public BillDTO getAllBills() {
        List<Bill> bills = billRepository.findAll();
        return convertBillToDTO((Bill) bills);
    }

}
