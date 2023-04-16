package com.example.bank.Bank_Service.controller;

import com.example.bank.Bank_Service.model.TransactionStatus;
import com.example.bank.Bank_Service.model.entity.MasterBalanceEntity;
import com.example.bank.Bank_Service.model.entity.VisaBalanceEntity;
import com.example.bank.Bank_Service.repository.MasterBalanceRepository;
import com.example.bank.Bank_Service.repository.VisaBalanceRepository;
import com.example.bank.Bank_Service.rest.request.BankRequest;
import com.example.bank.Bank_Service.rest.request.TransactionRequest;
import com.example.bank.Bank_Service.rest.response.TransactionResponse;
import com.example.bank.Bank_Service.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/banking")
public class BalanceController {




    @Autowired
    private final BankService bankService;

    @PostMapping
    public TransactionResponse checkCard(@RequestBody TransactionRequest transactionRequest) {
        BankRequest request =  bankService.getCardDetails(transactionRequest.getCardId());

        TransactionResponse transactionResponse = bankService.checkCard(request , transactionRequest);


        return TransactionResponse.builder().transactionStatus(transactionResponse.getTransactionStatus()).transactionId(transactionResponse.getTransactionId()).build();
    }
    @GetMapping("/visa")
    public ResponseEntity getVisaBalances(Pageable pageable) {
        return ResponseEntity.ok(bankService.readVisaBalances(pageable));
    }
    @GetMapping("master")
    public ResponseEntity getMasterBalances(Pageable pageable) {
        return ResponseEntity.ok(bankService.readMasterBalances(pageable));
    }


}