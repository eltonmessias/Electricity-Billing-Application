package com.eltonmessias.Electricity_Billing.controller;

import com.eltonmessias.Electricity_Billing.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;


//    @GetMapping("/send-email")
//    public String sendEmail() throws MessagingException {
//        emailService.sendEmail("guambee43@gmail.com", "Elton Messias", "Factura", 25,22.2, "hee", "10.06", "2565");
//        return "Email sent";
//    }
}
