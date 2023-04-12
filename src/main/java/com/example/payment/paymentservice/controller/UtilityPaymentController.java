package com.example.payment.paymentservice.controller;

import com.example.payment.paymentservice.rest.request.UtilityPaymentRequest;
import com.example.payment.paymentservice.service.UtilityPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/utility-payments")
public class UtilityPaymentController {

    @Autowired
    private final UtilityPaymentService utilityPaymentService;

    @GetMapping
    public ResponseEntity readPayments(Pageable pageable) {
        return ResponseEntity.ok(utilityPaymentService.readPayments(pageable));
    }
    @PostMapping
    public ResponseEntity processPayment(@RequestBody UtilityPaymentRequest paymentRequest) {
        return ResponseEntity.ok(utilityPaymentService.utilPayment(paymentRequest));
    }

}