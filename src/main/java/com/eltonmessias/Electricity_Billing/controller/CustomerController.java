package com.eltonmessias.Electricity_Billing.controller;


import com.eltonmessias.Electricity_Billing.dto.ConsumerDTO;
import com.eltonmessias.Electricity_Billing.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private CustomerService customerService;


    @GetMapping("/consumers")
    public ResponseEntity<List<ConsumerDTO>> getConsumers() {
        return new ResponseEntity<>(customerService.getAllConsumers(), HttpStatus.OK);
    }

    @GetMapping("/consumers/{id}")
    public ResponseEntity<ConsumerDTO> getConsumer(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.getConsumerById(id), HttpStatus.OK);
    }

    @PutMapping("/consumers/{id}")
    public ResponseEntity<ConsumerDTO> updateConsumer( @RequestBody ConsumerDTO consumerDTO, @PathVariable Long id) {
        return new ResponseEntity<>(customerService.updateConsumer(consumerDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("customers/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.deleteConsumer(id), HttpStatus.OK);
    }

}
