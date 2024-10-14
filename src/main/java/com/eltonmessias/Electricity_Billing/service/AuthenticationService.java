package com.eltonmessias.Electricity_Billing.service;

import com.eltonmessias.Electricity_Billing.model.Consumer;
import com.eltonmessias.Electricity_Billing.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private ConsumerRepository consumerRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public String saveConsumer(Consumer consumer) {
        if (consumerRepository.findByEmail(consumer.getEmail()) == null) {
            consumer.setPassword(encoder.encode(consumer.getPassword()));
            consumerRepository.save(consumer);
            return "Consumer saved successfully";
        }
        return "Email already in use";
    }

    public String login(String email, String password) {
        Consumer consumer = consumerRepository.findByEmail(email);
        if (consumer == null) {
            return "Consumer not found";
        }
        if (!encoder.matches(password, consumer.getPassword())){
            return "Wrong password";
        }
        return "Consumer logged in successfully";
    }

}
