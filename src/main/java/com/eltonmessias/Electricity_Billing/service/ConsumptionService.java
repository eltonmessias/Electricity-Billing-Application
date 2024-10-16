package com.eltonmessias.Electricity_Billing.service;

import com.eltonmessias.Electricity_Billing.dto.ConsumptionDTO;
import com.eltonmessias.Electricity_Billing.model.Consumption;
import com.eltonmessias.Electricity_Billing.model.Customer;
import com.eltonmessias.Electricity_Billing.repository.ConsumptionRepository;
import com.eltonmessias.Electricity_Billing.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsumptionService {

    @Autowired
    private ConsumptionRepository consumptionRepository;
    @Autowired
    private CustomerRepository customerRepository;

    private ConsumptionDTO convertToDTO(Consumption consumption) {
        return new ConsumptionDTO(consumption.getId(), consumption.getCustomer().getId(), consumption.getConsumptionInKwh(), consumption.getReadingDate(), consumption.getCostPerUnit(), consumption.getTotalCost());
    }

    private Consumption convertToEntity(ConsumptionDTO consumptionDTO) {
        Consumption consumption = new Consumption();

        BeanUtils.copyProperties(consumptionDTO, consumption);
        return consumption;
    }

    public String registerConsumption(ConsumptionDTO consumptionDTO) {
        Consumption consumption = new Consumption();

        Customer customer = customerRepository.findById(consumptionDTO.customer_id()).orElseThrow(() -> new RuntimeException("Customer not found"));

       consumption.setCustomer(customer);
        consumption.setConsumptionInKwh(consumptionDTO.consumptionInKwh());
        consumption.setReadingDate(consumptionDTO.readingDate());
        consumption.setCostPerUnit(consumptionDTO.costPerUnit());
        consumption.setTotalCost(consumptionDTO.totalCost());

        try {
            consumptionRepository.save(consumption);
            return "Consumption registered successfully";
        } catch (Exception e) {
            return "Consumption could not be registered: " + e.getMessage();
        }
    }



    public List<ConsumptionDTO> getAllConsumptions() {
        return consumptionRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
