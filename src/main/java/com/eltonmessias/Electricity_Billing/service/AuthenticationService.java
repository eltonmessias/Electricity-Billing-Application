package com.eltonmessias.Electricity_Billing.service;

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

    public String saveConsumer(Customer consumer) {
        if (consumerRepository.findByEmail(consumer.getEmail()) == null) {
            consumer.setPassword(encoder.encode(consumer.getPassword()));
            consumerRepository.save(consumer);
            return "Consumer saved successfully";
        }
        return "Email already in use";
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
