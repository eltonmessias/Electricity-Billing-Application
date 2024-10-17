package com.eltonmessias.Electricity_Billing.controller;

import com.eltonmessias.Electricity_Billing.dto.ConsumptionDTO;
import com.eltonmessias.Electricity_Billing.service.ConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ConsumptionController {

    @Autowired
    private ConsumptionService consumptionService;

    @PostMapping("/consumptions")
    public String SaveConsumption(@RequestBody ConsumptionDTO consumptionDTO) {
        return consumptionService.registerConsumption(consumptionDTO);
    }

    @GetMapping("/readings")
    public ResponseEntity<List<ConsumptionDTO>> getReadings() {
        return new ResponseEntity<>(consumptionService.getAllConsumptions(), HttpStatus.OK);
    }

}
