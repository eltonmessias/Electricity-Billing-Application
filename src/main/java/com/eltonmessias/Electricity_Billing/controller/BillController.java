package com.eltonmessias.Electricity_Billing.controller;

import com.eltonmessias.Electricity_Billing.dto.BillDTO;
import com.eltonmessias.Electricity_Billing.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping("/{id}")
    public ResponseEntity<BillDTO> generateBill(@PathVariable("id") long id) {
        return new ResponseEntity<>(billService.createBill(id), HttpStatus.CREATED);

    }

    @GetMapping("/")
    public BillDTO getBills() {
        return billService.getAllBills();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillDTO> getBillById(@PathVariable("id") long id) {
        return new ResponseEntity<>(billService.getBillById(id), HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<BillDTO>> getBillsByCustomerId(@PathVariable("customerId") long customerId) {
        List<BillDTO> bills = billService.getAllBillsByCustomerId(customerId);
        if(bills.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

}
