package com.example.reports.controller;

import com.example.reports.dto.Mapper;
import com.example.reports.dto.TransactionDTO;
import com.example.reports.dto.TransactionRequest;
import com.example.reports.model.Transaction;
import com.example.reports.service.TransactionNotificationService;
import com.example.reports.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionNotificationService transactionNotificationService;

    @Autowired
    TransactionService transactionServiceImpl;
    @Autowired
    Mapper mapper;

    @PostMapping("/transaction")
    public ResponseEntity<?> create(@RequestBody TransactionDTO transactionDTO){
        Transaction t = mapper.DTOtoTransaction(transactionDTO);
        TransactionDTO newTransaction = transactionServiceImpl.createTransaction(t);
        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/history")
    public ResponseEntity<?> userHistory(@PathVariable long userId){
        List<Transaction> transactionList = transactionServiceImpl.createOrderHistory(userId);
        return new ResponseEntity<>(transactionList, HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<TransactionRequest> notify(@RequestBody TransactionRequest transactionDto){
        System.out.println("test");
        transactionNotificationService.broadcastPaymentComplete(transactionDto);
        return ResponseEntity.ok(transactionDto);
    }
}
