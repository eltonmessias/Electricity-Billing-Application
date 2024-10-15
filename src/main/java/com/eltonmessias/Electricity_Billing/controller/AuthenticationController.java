package com.eltonmessias.Electricity_Billing.controller;

import com.eltonmessias.Electricity_Billing.model.Customer;
import com.eltonmessias.Electricity_Billing.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> createConsumer(@RequestBody Customer consumer) {
        return new ResponseEntity<>(authenticationService.saveConsumer(consumer), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Customer consumer) {
        return new ResponseEntity<>(authenticationService.login(consumer.getEmail(), consumer.getPassword()), HttpStatus.ACCEPTED);
    }
}
