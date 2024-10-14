package com.eltonmessias.Electricity_Billing.controller;


import com.eltonmessias.Electricity_Billing.dto.ConsumerDTO;
import com.eltonmessias.Electricity_Billing.repository.ConsumerRepository;
import com.eltonmessias.Electricity_Billing.service.ConsumerService;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ConsumerController {
    @Autowired
    private ConsumerService consumerService;


    @GetMapping("/consumers")
    public ResponseEntity<List<ConsumerDTO>> getConsumers() {
        return new ResponseEntity<>(consumerService.getAllConsumers(), HttpStatus.OK);
    }

}
