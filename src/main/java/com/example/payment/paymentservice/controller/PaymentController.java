package com.example.payment.paymentservice.controller;

import com.example.payment.paymentservice.rest.request.PaymentRequest;
import com.example.payment.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/payments")
public class PaymentController {

    @Autowired
    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity readPayments(Pageable pageable) {
        return ResponseEntity.ok(paymentService.readPayments(pageable));
    }
    @PostMapping
    public ResponseEntity processPayment(@RequestBody PaymentRequest paymentRequest) {
        return ResponseEntity.ok(paymentService.utilPayment(paymentRequest));
    }

}