package com.eltonmessias.Electricity_Billing.service;
import com.eltonmessias.Electricity_Billing.dto.StripeChargeDto;
import com.eltonmessias.Electricity_Billing.dto.StripeTokenDTO;

import com.eltonmessias.Electricity_Billing.enums.BillStatus;
import com.eltonmessias.Electricity_Billing.exception.ResourceNotFoundException;
import com.eltonmessias.Electricity_Billing.model.Bill;
import com.eltonmessias.Electricity_Billing.repository.BillRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
public class StripeService {

    @Autowired
    private BillRepository billRepository;


    @Value("${api.stripe.key}")
    private  String stripeApiKey;

    public StripeService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;

    }


//    public StripeTokenDTO createCardToken(StripeTokenDTO model) {
//        try {
//            Map<String, Object> card = new HashMap<>();
//            card.put("number", model.getCardNumber());
//            card.put("exp_month", model.getExpMonth());
//            card.put("exp_year", model.getExpYear());
//            card.put("cvc", model.getCvc());
//            Map<String, Object> params = new HashMap<>();
//            params.put("card", card);
//            Token token = Token.create(params);
//            if (token != null && token.getId() != null) {
//                model.setSuccess(true);
//                model.setToken(token.getId());
//            }
//            return model;
//        } catch (StripeException e) {
//            log.error("StripeService (createCardToken) ", e);
//            throw new RuntimeException(e.getMessage());
//        }
//    }

    public StripeTokenDTO createCardToken(StripeTokenDTO model) {
        try {
            // Utilizando o token de teste em vez dos dados de cartão para simulação segura
            String testToken = "tok_visa"; // Token de teste fornecido pelo Stripe
            model.setSuccess(true);
            model.setToken(testToken);
            return model;
        } catch (Exception e) {
            log.error("StripeService (createCardToken)", e);
            throw new RuntimeException("Erro ao criar o token: " + e.getMessage());
        }
    }

//    public StripeChargeDto charge(StripeChargeDto chargeRequest) {
//
//
//        try {
//            chargeRequest.setSuccess(false);
//            Map<String, Object> chargeParams = new HashMap<>();
//            chargeParams.put("amount", (int) (chargeRequest.getAmount() * 100));
//            chargeParams.put("currency", "USD");
//            chargeParams.put("description", "Payment for id " + chargeRequest.getAdditionalInfo().getOrDefault("ID_TAG", ""));
//            chargeParams.put("source", chargeRequest.getStripeToken());
//            Map<String, Object> metaData = new HashMap<>();
//            metaData.put("id", chargeRequest.getChargeId());
//            metaData.putAll(chargeRequest.getAdditionalInfo());
//            chargeParams.put("metadata", metaData);
//            Charge charge = Charge.create(chargeParams);
//            chargeRequest.setMessage(charge.getOutcome().getSellerMessage());
//
//            if (charge.getPaid()) {
//                chargeRequest.setChargeId(charge.getId());
//                chargeRequest.setSuccess(true);
//
//            }
//            return chargeRequest;
//        } catch (StripeException e) {
//            log.error("StripeService (charge)", e);
//            throw new RuntimeException(e.getMessage());
//        }
//
//    }



    public StripeChargeDto charge(Long billId, StripeChargeDto chargeRequest) {
        try {

            Bill bill = billRepository.findById(billId).orElseThrow(() -> new ResourceNotFoundException("Bill not found"));
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", (int) (bill.getAmountDue() * 100));
            chargeParams.put("currency", "usd");
            chargeParams.put("description", "Payment for Bill ID " + billId);
            chargeParams.put("source", chargeRequest.getStripeToken());

            Map<String, Object> metaData = new HashMap<>();
            metaData.put("bill_id", billId);
            metaData.put("Customer_name", bill.getCustomer().getFirstName());
            metaData.put("Customer_email", bill.getCustomer().getEmail());
            chargeParams.put("metadata", metaData);

            Charge charge = Charge.create(chargeParams);
            chargeRequest.setMessage(charge.getOutcome().getSellerMessage());

            if (charge.getPaid()) {
                chargeRequest.setChargeId(charge.getId());
                chargeRequest.setSuccess(true);
                bill.setStatus(BillStatus.valueOf("PAID"));
                billRepository.save(bill);
            }

            return chargeRequest;

        } catch (StripeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
