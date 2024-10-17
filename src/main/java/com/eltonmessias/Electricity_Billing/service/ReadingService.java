package com.eltonmessias.Electricity_Billing.service;

import com.eltonmessias.Electricity_Billing.dto.ReadingDTO;
import com.eltonmessias.Electricity_Billing.model.Reading;
import com.eltonmessias.Electricity_Billing.model.Customer;
import com.eltonmessias.Electricity_Billing.repository.ReadingRepository;
import com.eltonmessias.Electricity_Billing.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReadingService {

    @Autowired
    private ReadingRepository readingRepository;
    @Autowired
    private CustomerRepository customerRepository;

    private ReadingDTO convertToDTO(Reading consumption) {
        return new ReadingDTO(consumption.getId(), consumption.getCustomer().getId(), consumption.getConsumptionInKwh(), consumption.getReadingDate(), consumption.getCostPerUnit(), consumption.getTotalCost());
    }

    private Reading convertToEntity(ReadingDTO consumptionDTO) {
        Reading consumption = new Reading();

        BeanUtils.copyProperties(consumptionDTO, consumption);
        return consumption;
    }

    public String registerConsumption(ReadingDTO consumptionDTO) {
        Reading reading = new Reading();

        Customer customer = customerRepository.findById(consumptionDTO.customer_id()).orElseThrow(() -> new RuntimeException("Customer not found"));

        reading.setCustomer(customer);
        reading.setConsumptionInKwh(consumptionDTO.consumptionInKwh());
        reading.setReadingDate(consumptionDTO.readingDate());
        reading.setCostPerUnit(consumptionDTO.costPerUnit());
        reading.setTotalCost(consumptionDTO.totalCost());

        try {
            readingRepository.save(reading);
            return "Consumption registered successfully";
        } catch (Exception e) {
            return "Consumption could not be registered: " + e.getMessage();
        }
    }


    public List<ReadingDTO> getAllConsumptions() {
        return readingRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ReadingDTO getReadingById(long id) {
        return convertToDTO(readingRepository.findById(id).orElseThrow(() -> new RuntimeException("Reading not found")));
    }
}
