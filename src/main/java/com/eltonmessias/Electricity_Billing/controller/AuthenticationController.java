package com.eltonmessias.Electricity_Billing.controller;

import com.eltonmessias.Electricity_Billing.model.Customer;
import com.eltonmessias.Electricity_Billing.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.AuthenticationException;

@Controller
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> createConsumer(@RequestBody Customer consumer) {
        return new ResponseEntity<>(authenticationService.saveConsumer(consumer), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String>login(@RequestBody Customer consumer) throws AuthenticationException {
        return authenticationService.login(consumer.getEmail(), consumer.getPassword());
    }
}
