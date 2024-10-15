package com.eltonmessias.Electricity_Billing.service;

import com.eltonmessias.Electricity_Billing.dto.ConsumerDTO;
import com.eltonmessias.Electricity_Billing.exception.ResourceNotFoundException;
import com.eltonmessias.Electricity_Billing.model.Customer;
import com.eltonmessias.Electricity_Billing.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {


    @Autowired
    private CustomerRepository customerRepository;

    private ConsumerDTO convertToDTO(Customer consumer) {
        return new ConsumerDTO(consumer.getId(), consumer.getFirstName(), consumer.getLastName(), consumer.getEmail(), consumer.getAddress());
    }

    private Customer convertToConsumer(ConsumerDTO consumerDTO) {
        Customer consumer = new Customer();
        BeanUtils.copyProperties(consumerDTO, consumer);
        return consumer;
    }

    public List<ConsumerDTO> getAllConsumers() {
        return customerRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ConsumerDTO getConsumerById(Long id) {
        return convertToDTO(customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Consumer with id " + id + " not found")));
    }

    public ConsumerDTO updateConsumer(ConsumerDTO consumerDTO, Long id) {
        Customer consumer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Consumer with id " + id + " not found"));

        consumer.setFirstName(consumerDTO.firstName());
        consumer.setLastName(consumerDTO.lastName());
        consumer.setEmail(consumerDTO.email());
        consumer.setAddress(consumerDTO.address());

        return convertToDTO(customerRepository.save(consumer));
    }

    public String deleteConsumer(Long id) {
        Customer consumer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Consumer with id " + id + "not found"));
        customerRepository.delete(consumer);
        return "Consumer deleted";
    }


}
