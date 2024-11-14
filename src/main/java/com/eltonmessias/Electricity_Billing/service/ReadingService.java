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

    private final double costPerUnitKwh = 20.0;

    private ReadingDTO convertToDTO(Reading consumption) {
        return new ReadingDTO(consumption.getId(), consumption.getCustomer().getId(), consumption.getConsumptionInKwh(), consumption.getReadingDate(), consumption.getCostPerUnit(), consumption.getTotalCost());
    }

    private Reading convertToEntity(ReadingDTO consumptionDTO) {
        Reading consumption = new Reading();

        BeanUtils.copyProperties(consumptionDTO, consumption);
        return consumption;
    }

    public Object registerConsumption(ReadingDTO consumptionDTO) {
        Reading reading = new Reading();

        updateReading(consumptionDTO, reading);

        try {
            Reading read =  readingRepository.save(reading);
            return convertToDTO(read);
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

    public ReadingDTO updateReading(ReadingDTO readingDTO, long id) {
        Reading reading = readingRepository.findById(id).orElseThrow(() -> new RuntimeException("Reading not found"));
        updateReading(readingDTO, reading);
        readingRepository.save(reading);
        return convertToDTO(reading);

    }

    private void updateReading(ReadingDTO readingDTO, Reading reading) {
        Customer customer = customerRepository.findById(readingDTO.customer_id()).orElseThrow(() -> new RuntimeException("Customer not found"));
        reading.setCustomer(customer);
        reading.setConsumptionInKwh(readingDTO.consumptionInKwh());
        reading.setReadingDate(readingDTO.readingDate());
        reading.setCostPerUnit(costPerUnitKwh);
        reading.setTotalCost(costPerUnitKwh * readingDTO.consumptionInKwh());
    }

    public void deleteReading(long id) {
        Reading reading = readingRepository.findById(id).orElseThrow(() -> new RuntimeException("Reading not found"));
        readingRepository.delete(reading);
    }
}
