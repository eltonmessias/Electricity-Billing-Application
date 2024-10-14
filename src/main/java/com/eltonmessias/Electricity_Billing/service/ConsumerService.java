package com.eltonmessias.Electricity_Billing.service;

import com.eltonmessias.Electricity_Billing.dto.ConsumerDTO;
import com.eltonmessias.Electricity_Billing.exception.ResourceNotFoundException;
import com.eltonmessias.Electricity_Billing.model.Consumer;
import com.eltonmessias.Electricity_Billing.repository.ConsumerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsumerService {


    @Autowired
    private ConsumerRepository consumerRepository;

    private ConsumerDTO convertToDTO(Consumer consumer) {
        return new ConsumerDTO(consumer.getId(), consumer.getFirstName(), consumer.getLastName(), consumer.getEmail(), consumer.getAddress());
    }

    private Consumer convertToConsumer(ConsumerDTO consumerDTO) {
        Consumer consumer = new Consumer();
        BeanUtils.copyProperties(consumerDTO, consumer);
        return consumer;
    }

    public List<ConsumerDTO> getAllConsumers() {
        return consumerRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ConsumerDTO getConsumerById(Long id) {
        return convertToDTO(consumerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Consumer with id " + id + " not found")));
    }

    public ConsumerDTO updateConsumer(ConsumerDTO consumerDTO, Long id) {
        Consumer consumer = consumerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Consumer with id " + id + " not found"));

        consumer.setFirstName(consumerDTO.firstName());
        consumer.setLastName(consumerDTO.lastName());
        consumer.setEmail(consumerDTO.email());
        consumer.setAddress(consumerDTO.address());

        return convertToDTO(consumerRepository.save(consumer));
    }


}
