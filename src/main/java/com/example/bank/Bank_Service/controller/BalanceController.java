package com.example.bank.Bank_Service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/banking")
public class BalanceController {

    @GetMapping
    public ResponseEntity getBalance() {
        return ResponseEntity.ok("this is from Banking");
    }


//    @Autowired
//    private final TransactionService transactionService;
//
//    @GetMapping
//    public ResponseEntity readTransactions(Pageable pageable) {
//        return ResponseEntity.ok(transactionService.readTransactions(pageable));
//    }
//    @PostMapping
//    public ResponseEntity processTransaction(@RequestBody TransactionRequest transactionRequest) {
//        return ResponseEntity.ok(transactionService.utilPayment(transactionRequest));
//    }
//
//    @GetMapping("/{id}")
//    public Object getTransactionById(@PathVariable Long id){
//        try {
//            TransactionDto transaction = transactionService.getTransactionById(id);
//            return ResponseEntity.ok(transaction);
//        } catch (ChangeSetPersister.NotFoundException er){
//            return new ResponseEntity<>("Transaction not found", HttpStatus.NOT_FOUND);
//        }
//
//    }

}