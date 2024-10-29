package com.eltonmessias.Electricity_Billing.controller;

import com.eltonmessias.Electricity_Billing.dto.StripeChargeDto;
import com.eltonmessias.Electricity_Billing.dto.StripeTokenDTO;
import com.eltonmessias.Electricity_Billing.service.StripeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stripe")
@AllArgsConstructor
public class StripeController {

    @Autowired
    private StripeService stripeService;


    @PostMapping("/card/token")
    @ResponseBody
    public StripeTokenDTO createCardToken(@RequestBody StripeTokenDTO stripeTokenDTO) {
        return stripeService.createCardToken(stripeTokenDTO);
    }

    @PostMapping("/charge")
    @ResponseBody
    public StripeChargeDto charge(@RequestBody StripeChargeDto stripeChargeDto, @RequestParam Long billId) {
        return stripeService.charge(billId, stripeChargeDto);
    }

}
