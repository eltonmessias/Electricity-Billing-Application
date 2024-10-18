package com.eltonmessias.Electricity_Billing.controller;

import com.eltonmessias.Electricity_Billing.dto.BillDTO;
import com.eltonmessias.Electricity_Billing.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping("/bills/{id}")
    public ResponseEntity<BillDTO> generateBill(@PathVariable("id") long id) {
        return new ResponseEntity<>(billService.createBill(id), HttpStatus.CREATED);

    }

    @GetMapping("/bills")
    public BillDTO getBills() {
        return billService.getAllBills();
    }

    @GetMapping("/bills/{id}")
    public ResponseEntity<BillDTO> getBillById(@PathVariable("id") long id) {
        return new ResponseEntity<>(billService.getBillById(id), HttpStatus.OK);
    }

}
