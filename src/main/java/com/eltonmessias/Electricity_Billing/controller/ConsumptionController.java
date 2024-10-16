package com.eltonmessias.Electricity_Billing.controller;

import com.eltonmessias.Electricity_Billing.dto.ConsumptionDTO;
import com.eltonmessias.Electricity_Billing.service.ConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ConsumptionController {

    @Autowired
    private ConsumptionService consumptionService;

    @PostMapping("/consumptions")
    public String SaveConsumption(@RequestBody ConsumptionDTO consumptionDTO) {
        return consumptionService.registerConsumption(consumptionDTO);
    }

}
