package com.eltonmessias.Electricity_Billing.controller;

import com.eltonmessias.Electricity_Billing.dto.BillDTO;
import com.eltonmessias.Electricity_Billing.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BillController {

    @Autowired
    private BillService billService;


    public ResponseEntity<BillDTO> generateBill(@PathVariable("id") long id) {
        return new ResponseEntity<>(billService.createBill(id), HttpStatus.CREATED);



    }
}
