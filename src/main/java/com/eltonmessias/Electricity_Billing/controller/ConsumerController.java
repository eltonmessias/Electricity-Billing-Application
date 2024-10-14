package com.eltonmessias.Electricity_Billing.controller;

import com.eltonmessias.Electricity_Billing.model.Consumer;
import com.eltonmessias.Electricity_Billing.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @PostMapping("/register")
    public ResponseEntity<String> createConsumer(@RequestBody Consumer consumer) {
        return new ResponseEntity<>(consumerService.saveConsumer(consumer), HttpStatus.CREATED);
    }
}
