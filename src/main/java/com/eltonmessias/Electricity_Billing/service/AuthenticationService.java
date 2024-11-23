package com.eltonmessias.Electricity_Billing.service;

import com.eltonmessias.Electricity_Billing.exception.ResourceNotFoundException;
import com.eltonmessias.Electricity_Billing.model.Customer;
import com.eltonmessias.Electricity_Billing.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.naming.AuthenticationException;

@Service
public class AuthenticationService {

    @Autowired
    private CustomerRepository consumerRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Customer saveCustomer(Customer consumer) {

        try {
            if (consumerRepository.findByEmail(consumer.getEmail()) == null) {
                consumer.setPassword(encoder.encode(consumer.getPassword()));
            }                return consumerRepository.save(consumer);

        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    public ResponseEntity<String> login(String email, String password) throws AuthenticationException {

        try {
            Customer consumer = consumerRepository.findByEmail(email);
            if (consumer != null && encoder.matches(password, consumer.getPassword())) {
                return ResponseEntity.ok("User logged in successfully");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }
        } catch (Exception e) {
            throw new AuthenticationException("Invalid email or password");
        }
    }

}
